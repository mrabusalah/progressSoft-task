var gulp = require('gulp');
var jshint = require('gulp-jshint');

gulp.task('lint', function() {
    return gulp.src('src/*.js')
        .pipe(jshint({
            "browserify": true
        }))
        .pipe(jshint.reporter('default'));
});