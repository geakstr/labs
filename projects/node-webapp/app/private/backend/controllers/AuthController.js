var Router = require('koa-router'),
  mount = require('koa-mount'),
  LocalStrategy = require('passport-local').Strategy,
  models = require('../models'),
  password_hash = require('password-hash'),
  crypto = require('crypto');

var AuthController = (function() {
  function AuthController(passport) {
    this._url = '/auth';
    this._passport = passport;

    this._passport.serializeUser(function(user, done) {
      done(null, user);
    });
    this._passport.deserializeUser(function(user, done) {
      done(null, user);
    });
    this._passport.use(new LocalStrategy({
        usernameField: 'email',
        passwordField: 'pass'
      },
      function(email, pass, done) {
        models.User.find({
          where: {
            email: email
          }
        }).success(function(user) {
          if (user === null) {
            return done(null, false, {
              message: 'Incorrect email'
            });
          }
          if (!password_hash.verify(pass, user.values.pass)) {
            return done(null, false, {
              message: 'Incorrect password'
            });
          }
          return done(null, user);
        }).error(function(err) {
          return done(err);
        });
      }));
  }

  AuthController.prototype._login = function(response, message) {
    return this._passport.authenticate('local', function * (err, user, info) {
      if (err) throw err;
      if (user === false) {
        response.status = 401;
        response.flash = {
          error: message
        };
        response.redirect('/login');
      } else {
        yield response.login(user);
        response.session.user_id = user.values.id;
        response.redirect('/');
      }
    });
  };

  /**
   * Serve "/auth"
   */
  AuthController.prototype.route = function(app) {
    var router = new Router(),
      passport = this._passport,
      controller = this;

    /**
     * Serve "/auth/signup"
     * @method POST
     */
    router.post('/signup', function * () {
      var request = this.request.body;

      var pass = request.pass.trim(),
        login = request.login.trim(),
        email = request.email.trim(),
        fio = request.fio.trim();

      if (login.length === 0 || email.length === 0 || fio.length === 0 || pass.length === 0) {
        this.flash = {
          error: "Нужно заполнить все поля"
        };
        this.redirect('/signup');
        return;
      }
      if (pass.length < 6) {
        this.flash = {
          error: "Слишком короткий пароль"
        };
        this.redirect('/signup');
        return;
      }

      // Проверяем, занят ли логин
      var count_user_with_login = yield models.User.count({
        where: {
          login: login
        }
      });
      if (count_user_with_login !== 0) {
        this.flash = {
          error: "Указанный вами логин уже занят"
        };
        this.redirect('/signup');
        return;
      }

      // Проверяем, занята ли почта
      var count_user_with_email = yield models.User.count({
        where: {
          email: email
        }
      });
      if (count_user_with_email !== 0) {
        this.flash = {
          error: "Указанная вами электронная почта уже занята"
        };
        this.redirect('/signup');
        return;
      }

      var mail_token = yield this.mailer.gen_token(email);

      yield models.User.create({
        pass: password_hash.generate(pass),
        fio: fio,
        login: login,
        email: email,
        mail_token: mail_token
      });

      yield this.mailer.send_approve_message(fio, email, mail_token);

      var flash = 'Регистрация не удалась.';
      flash += ' Возможно, данный адрес электронной почты уже занят.';
      yield controller._login(this, flash).call(this);
    });

    /**
     * Serve "/auth/login"
     * @method POST
     */
    router.post('/login', function * () {
      var flash = 'Не нашли такой комбинации электронной почты и пароля';
      yield controller._login(this, flash).call(this);
    });

    /**
     * Serve "/auth/logout"
     * @method  GET
     */
    router.get('/logout', function * () {
      this.session = null;
      this.logout();
      this.redirect('/');
    });

    /**
     * Serve "/auth/approve/:token"
     * @method  GET
     */
    router.get('/approve/:token', function * () {
      var token = this.params.token;

      var user = yield models.User.find({
        where: {
          mail_token: token
        }
      });
      if (user) {
        user.mail_approved = true;
        yield user.save();
      }

      this.redirect('/');
    });

    app.use(mount(this._url, router.middleware()));
  };

  return AuthController;
})();

module.exports = AuthController;