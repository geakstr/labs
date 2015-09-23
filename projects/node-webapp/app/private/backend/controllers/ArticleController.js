var Router = require('koa-router'),
  mount = require('koa-mount'),
  models = require('../models'),
  markdown = require("markdown").markdown;

var ArticleController = (function() {
  function ArticleController() {
    this._url = "/articles";
  }

  ArticleController.prototype.route = function(app) {
    var router = new Router();

    /**
     * Serve "/articles/compose"
     * @method  GET
     */
    router.get('/compose', function * () {
      yield this.render('articles/compose', {
        authenticated: this.isAuthenticated()
      });
    });


    var parse_tags = function(tags_string) {
      return tags_string.split(',').map(function(tag) {
        return tag.trim();
      }).filter(function(tag) {
        return tag.length > 0
      });
    }

    /**
     * Serve "/articles/compose" address
     * @method  POST
     */
    router.post('/compose', function * () {
      var request = this.request.body,
        title = request.title,
        text = request.text,
        tags = parse_tags(request.tags),
        visible = parseInt(request.visible) === 1 ? true : false;

      for (var i = 0; i < tags.length; i++) {
        tags[i] = yield models.Tag.findOrCreate({
          title: tags[i]
        });
      }

      var article = yield models.Article.create({
        text: text,
        html_text: markdown.toHTML(text),
        title: title,
        visible: visible
      });

      yield article.setTags(tags);
      yield article.setUser(yield models.User.find(this.session.user_id));

      this.redirect('/');
    });

    /**
     * Serve "/articles/:id"
     * @method  GET
     */
    router.get('/:id', function * () {
      var id = this.params.id;
      var article = yield models.Article.find({
        where: {
          id: id
        },
        include: [models.User, models.Tag]
      });

      if (!article.visible && article.user.id !== this.session.user_id) {
        this.redirect('/');
        return;
      }
      yield this.render('articles/article', {
        article: article,
        editable: article.user.id === this.session.user_id,
        authenticated: this.isAuthenticated(),
        user_id: this.session.user_id
      });
    });

    /**
     * Serve "/articles/:id/edit"
     * @method  GET
     */
    router.get('/:id/edit', function * () {
      var id = this.params.id,
        article = yield models.Article.find({
          where: {
            id: id
          },
          include: [models.User]
        }),
        tags = yield article.getTags();

      if (article.user.id !== this.session.user_id) {
        this.redirect('/');
        return;
      }

      var tags_string = tags.reduce(function(previousValue, currentValue, index, array) {
        return previousValue + currentValue.title + ", ";
      }, "");
      tags_string = tags_string.substring(0, tags_string.length - 2);

      yield this.render('articles/edit', {
        article: article,
        tags: tags_string,
        authenticated: this.isAuthenticated()
      });
    });

    /**
     * Serve "/articles/:id/edit"
     * @method  POST
     */
    router.post('/:id/edit', function * () {
      var id = this.params.id,
        article = yield models.Article.find({
          where: {
            id: id
          },
          include: [models.User]
        }),
        request = this.request.body,
        title = request.title,
        text = request.text,
        visible = parseInt(request.visible) === 1 ? true : false,
        tags = parse_tags(request.tags);

      if (article.user.id !== this.session.user_id) {
        this.redirect('/');
        return;
      }

      for (var i = 0; i < tags.length; i++) {
        tags[i] = yield models.Tag.findOrCreate({
          title: tags[i]
        });
      }

      article.title = title;
      article.text = text;
      article.html_text = markdown.toHTML(text);
      article.visible = visible;
      yield article.save();
      yield article.setTags(tags);
      this.redirect('/articles/' + id);
    });

    app.use(mount(this._url, router.middleware()));
  };

  return ArticleController;
})();

module.exports = ArticleController;