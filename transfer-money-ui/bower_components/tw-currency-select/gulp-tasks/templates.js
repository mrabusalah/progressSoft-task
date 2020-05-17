var gulp = require('gulp');
var templateCache = require('gulp-angular-templatecache');

gulp.task('templates', ['bower'], function () {
    return gulp.src('src/templates/*.html')
        .pipe(templateCache('tw-currency-select-templates.js', {
            standalone: true,
            module: 'tw-currency-select-templates',
            root: 'templates/',
            moduleSystem: 'Browserify'
        }))
        .pipe(gulp.dest('build'));
});
