var Router = require('koa-router'),
  mount = require('koa-mount'),
  models = require('../models');

var TagController = (function() {
  function TagController() {
    this._url = "/tags";
  }

  TagController.prototype.route = function(app) {
    var router = new Router();

    /**
     * Serve "/tags"
     * @method  GET
     */
    router.get('/', function * () {
      yield this.render('tags/index', {
        tags: yield models.Tag.findAll(),
        authenticated: this.isAuthenticated(),
        user_id: this.session.user_id
      });
    });

    /**
     * Serve "/tags/:id"
     * @method  GET
     */
    router.get('/:id', function * () {
      var id = this.params.id;
      var tag = yield models.Tag.find(id);
      var articles = yield tag.getArticles();

      yield this.render('tags/articles_by_tag', {
        tag: tag,
        articles: articles,
        authenticated: this.isAuthenticated(),
        user_id: this.session.user_id
      })
    });

    app.use(mount(this._url, router.middleware()));
  };

  return TagController;
})();

module.exports = TagController;