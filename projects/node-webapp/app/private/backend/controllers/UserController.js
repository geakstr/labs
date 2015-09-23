var Router = require('koa-router'),
  mount = require('koa-mount'),
  models = require('../models'),
  password_hash = require('password-hash');

var UserController = (function(app) {
  function UserController() {
    this._url = "/users";
  }

  /**
   * Serve "/user" addresses
   */
  UserController.prototype.route = function(app) {
    var router = new Router();
    /**
     * Serve "/users"
     * @method  GET
     */
    router.get('/', function * () {
      yield this.render('users/index', {
        users: yield models.User.findAll(),
        authenticated: this.isAuthenticated()
      });
    });

    /**
     * Serve "/users/profile"
     * @method  GET
     */
    router.get('/profile', function * () {
      var user = yield models.User.find({
        where: {
          id: this.session.user_id
        }
      });

      if (user === null) {
        this.redirect('/auth/logout');
        return;
      }

      yield this.render('users/profile/index', {
        user: user.values,
        mail_approved: user.mail_approved,
        authenticated: this.isAuthenticated(),
        user_id: this.session.user_id
      });
    });

    /**
     * Serve "/users/profile/:username"
     * @method  GET
     */
    router.get('/profile/:username', function * () {
      var username = this.params.username;
      var user = yield models.User.find({
        where: {
          login: username
        }
      });
      if (user !== null) {
        yield this.render('users/profile/username', {
          user: user,
          authenticated: this.isAuthenticated()
        });
      }
    });

    /**
     * Serve "/users/profile/:username/articles"
     * @method  GET
     */
    router.get('/profile/:username/articles', function * () {
      var username = this.params.username;
      var author = yield models.User.find({
        where: {
          login: username
        }
      });
      var blocked = false;
      if (author !== null) {
        var articles = null;
        if (author.id === this.session.user_id || this.session.user_id === 1) {
          articles = yield author.getArticles({
            order: [
              ['createdAt', 'DESC']
            ],
            include: [models.User]
          });
        } else {
          articles = yield author.getArticles({
            order: [
              ['createdAt', 'DESC']
            ],
            include: [models.User],
            where: {
              visible: true,
              blocked: false
            }
          });
        }
        yield this.render('articles/index', {
          authenticated: this.isAuthenticated(),
          content: articles,
          user_id: this.session.user_id
        });
      }
    });

    /**
     * Serve "/users/profile/edit"
     * @method  POST
     */
    router.post('/profile/:username/edit', function * () {
      var request = this.request.body;

      var fio = request.fio.trim(),
        email = request.email.trim(),
        pass = request.pass.trim();

      if (email.length === 0) {
        this.flash = {
          error: "Поле «Электронная почта» должно быть заполнено"
        };
        this.redirect('/users/profile/edit');
        return;
      }

      if (fio.length === 0) {
        this.flash = {
          error: "Поле «ФИО» должно быть заполнено"
        };
        this.redirect('/users/profile/edit');
        return;
      }

      if (pass.length !== 0 && pass.length < 6) {
        this.flash = {
          error: "Слишком короткий пароль"
        };
        this.redirect('/users/profile/edit');
        return;
      }

      var user = yield models.User.find({
        where: {
          id: this.session.user_id
        }
      });
      var login = user.login;
      if (user) {
        user.fio = fio;
        if (pass.length !== 0) user.pass = password_hash.generate(pass);
        if (email !== user.email) {
          var count_user_with_email = yield models.User.count({
            where: {
              email: email
            }
          });
          if (count_user_with_email === 0) {
            user.email = email;
          } else {
            this.flash = {
              error: "Указанная вами электронная почта уже занята"
            };
            this.redirect('/users/profile/' + login + '/edit');
            return;
          }
        }
        yield user.save();
      }
      this.redirect('/users/profile/' + login + '/edit');
    });

    /**
     * Serve "/users/profile/edit"
     * @method GET
     */
    router.get('/profile/:username/edit', function * () {
      var user = yield models.User.find({
        where: {
          id: this.session.user_id
        }
      });
      if (user === null) {
        this.redirect('/auth/logout');
        return;
      }

      yield this.render('users/profile/edit', {
        user: user.values,
        flash: this.flash.error || null,
        authenticated: this.isAuthenticated()
      });
    });


    app.use(mount(this._url, router.middleware()));
  };

  return UserController;
})();

module.exports = UserController;