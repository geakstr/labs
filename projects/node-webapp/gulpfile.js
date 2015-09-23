var gulp = require('gulp'),
  stylus = require('gulp-stylus'),
  nodemon = require('gulp-nodemon'),
  run = require('gulp-run');

gulp.task('default', ['stylus', 'lmd-build', 'nodemon', 'watch']);

gulp.task('stylus', function() {
  gulp.src('app/private/frontend/stylus/*.styl')
    .pipe(stylus())
    .pipe(gulp.dest('app/public/static/css'));
});

gulp.task('lmd-build', function() {
  // run('lmd build index').exec()
  //   .pipe(gulp.dest('output'));
});

gulp.task('nodemon', function() {
  nodemon({
    script: 'app/private/backend/server.js',
    ext: 'html js',
    ignore: [
      './node_modules/**',
      'gulpfile.js',
      'app/public/static/js/*.js',
      'app/private/frontend/js/*.js'
    ],
    env: {
      'NODE_ENV': 'development'
    },
    nodeArgs: ['--harmony']
  });
});

gulp.task('watch', function() {
  gulp.watch('app/private/frontend/stylus/*.styl', ['stylus']);
  gulp.watch('app/private/js/*.js', ['lmd-build']);
});