var gulp = require('gulp');
var mainBowerFiles = require('main-bower-files');
//var runSequence = require('run-sequence');

gulp.task('example', ['example:js', 'example:css', 'example:bootstrap-fonts']);

gulp.task('example:js', ['bower'], function() {
    return gulp.src(mainBowerFiles('**/*.js'))
        .pipe(gulp.dest('example/vendor/js/'));
});

gulp.task('example:css', ['bower'], function() {
    return gulp.src(mainBowerFiles('**/*.css'))
        .pipe(gulp.dest('example/vendor/css/'));
});

gulp.task('example:bootstrap-fonts', ['bower'], function() {
    return gulp.src('bower_components/bootstrap/dist/fonts/*')
        .pipe(gulp.dest('example/vendor/fonts/'));
});

