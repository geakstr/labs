var Router = require('koa-router'),
  mount = require('koa-mount'),
  models = require('../models');

var AdminController = (function() {
  function AdminController() {
    this._url = "/admin";
  }

  AdminController.prototype.route = function(app) {
    var router = new Router();

    /**
     * Serve "/admin"
     * @method  GET
     */
    router.get('/', function * () {

    });

    /**
     * Serve "/admin/articles/:id/block"
     * @method  GET
     */
    router.get('/articles/:id/block', function * () {
      if (this.session.user_id !== 1) {
        this.redirect('/');
        return;
      }

      var id = this.params.id;
      var article = yield models.Article.find(id);

      article.blocked = true;
      yield article.save();

      this.redirect('/');
      return;
    });

    /**
     * Serve "/admin/articles/:id/unblock"
     * @method  GET
     */
    router.get('/articles/:id/unblock', function * () {
      if (this.session.user_id !== 1) {
        this.redirect('/');
        return;
      }

      var id = this.params.id;
      var article = yield models.Article.find(id);

      article.blocked = false;
      yield article.save();

      this.redirect('/');
      return;
    });

    /**
     * Serve "/admin/articles/blocked"
     * @method  GET
     */
    router.get('/articles/blocked', function * () {
      if (this.session.user_id !== 1) {
        this.redirect('/');
        return;
      }

      var id = this.params.id;
      var articles = yield models.Article.findAll({
        where: {
          blocked: true
        },
        order: [
          ['updatedAt', 'DESC', 'createdAt', 'DESC']
        ],
        include: [models.User]
      });

      yield this.render('articles/index', {
        content: articles,
        authenticated: this.isAuthenticated(),
        user_id: this.session.user_id
      });
      return;
    });

    /**
     * Serve "/admin/tags/:id/delete"
     * @method  GET
     */
    router.get('/tags/:id/delete', function * () {
      if (this.session.user_id !== 1) {
        this.redirect('/');
        return;
      }

      var id = this.params.id;
      var tag = yield models.Tag.find(id);
      yield tag.destroy();
      this.redirect('/');
      return;
    });

    app.use(mount(this._url, router.middleware()));
  };

  return AdminController;
})();

module.exports = AdminController;