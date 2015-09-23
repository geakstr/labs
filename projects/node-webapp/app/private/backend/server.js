var app = require('koa')(),
  Router = require('koa-router'),
  mount = require('koa-mount'),
  render = require('koa-ejs'),
  static_folder = require('koa-static'),
  passport = require('koa-passport'),
  body_parser = require('koa-bodyparser'),
  session = require('koa-session'),
  lodash = require('lodash'),
  models = require('./models'),
  flash = require('koa-flash'),
  mailer = require('./utils/Mailer.js');

app.keys = ['secretkey'];

app.use(Router(app));
app.use(static_folder(__dirname + '/../../public/static'));
app.use(session());
app.use(flash());
app.use(passport.initialize());
app.use(passport.session());
app.use(body_parser());

mailer(app, {
  service: 'Gmail',
  auth: {
    user: 'geakstrasu@gmail.com',
    pass: 'qwaszx123456'
  }
});

render(app, {
  root: __dirname + '/views',
  layout: 'layout',
  viewExt: 'html',
  cache: false,
  debug: true,
  locals: {},
  filters: {}
});

var IndexController = require('./controllers/IndexController'),
  AuthController = require('./controllers/AuthController'),
  UserController = require('./controllers/UserController'),
  ArticleController = require('./controllers/ArticleController'),
  TagController = require('./controllers/TagController'),
  AdminController = require('./controllers/AdminController');

new IndexController().route(app);
new AuthController(passport).route(app);
new UserController().route(app);
new ArticleController().route(app);
new TagController().route(app);
new AdminController().route(app);

app.use(function * (next) {
  if (this.isAuthenticated()) {
    yield next;
  } else {
    this.redirect('/');
  }
});

models.Article.belongsTo(models.User);
models.User.hasMany(models.Article);

models.Article.hasMany(models.Tag, {
  through: 'ArticleHasTags'
});

models.Tag.hasMany(models.Article, {
  through: 'ArticleHasTags'
});

models.sequelize
  .sync({
    force: false
  })
  .complete(function(err) {
    if (err) {
      throw err[0];
    } else {
      app.listen(3000);
    }
  });