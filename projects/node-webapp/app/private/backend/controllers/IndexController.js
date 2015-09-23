var Router = require('koa-router'),
  mount = require('koa-mount'),
  models = require('../models');

var IndexController = (function(app) {
  function IndexController() {
    this._url = "/";
  }

  /**
   * Serve "/" addresses
   */
  IndexController.prototype.route = function(app) {
    var router = new Router();

    /**
     * Serve "/search"
     * @method  GET
     */
    router.get('/search', function * () {
      var query = require('url').parse(this.request.url, true).query.q;

      var articles = yield models.Article.findAll({
        where: ["title LIKE ?", "%" + query + "%"],
        include: [models.User]
      });

      var users = yield models.User.findAll({
        where: ["login LIKE ?", "%" + query + "%"]
      });

      var tags = yield models.Tag.findAll({
        where: ["title LIKE ?", "%" + query + "%"]
      });

      yield this.render('index/search', {
        articles: articles,
        users: users,
        tags: tags,
        authenticated: this.isAuthenticated(),
        query: query,
        user_id: this.session.user_id
      });
    });

    /**
     * Serve "/" address
     */
    router.get('/', function * () {
      var articles = yield models.Article.findAll({
        order: [
          ['createdAt', 'DESC']
        ],
        include: [models.User],
        where: {
          visible: true,
          blocked: false
        }
      });
      if (this.isAuthenticated()) {
        var user_id = this.session.user_id;

        var user = yield models.User.find({
          where: {
            id: user_id
          }
        });

        if (user === null) {
          this.redirect('/auth/logout');
          return;
        }
        if (this.session.user_id === 1) {
          articles = yield models.Article.findAll({
            order: [
              ['createdAt', 'DESC']
            ],
            include: [models.User],
            where: {
              visible: true
            }
          });
        }
      }

      yield this.render('articles/index', {
        content: articles,
        authenticated: this.isAuthenticated(),
        user_id: user_id
      });
    });

    router.get('/login', function * () {
      yield this.render('index/login', {
        flash: this.flash.error || null,
        authenticated: this.isAuthenticated()
      });
    });

    router.get('/signup', function * () {
      yield this.render('index/signup', {
        flash: this.flash.error || null,
        authenticated: this.isAuthenticated()
      });
    });

    app.use(mount(this._url, router.middleware()));
  };

  return IndexController;
})();

module.exports = IndexController;