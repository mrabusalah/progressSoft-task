var gulp = require('gulp');
var rename = require('gulp-rename');
var remoteSrc = require('gulp-remote-src');

var FLAGS_FILE_NAME = 'flags.png';
var FLAGS_BASE_URI = 'https://transferwise.com/assets/common/flags/48/';

gulp.task('flags', function() {
    remoteSrc(
        [FLAGS_FILE_NAME], {
            base: FLAGS_BASE_URI,
            requestOptions: {
                headers: {
                    'User-Agent': 'request'
                },
                strictSSL: false
            }
        })
        .pipe(rename(FLAGS_FILE_NAME))
        .pipe(gulp.dest('./src/img/'));
});
