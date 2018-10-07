// Package and minify all input files separately
var shell = require('shelljs');
var files = process.argv.slice(2);

files.forEach(function (file) {
    shell.exec(`browserify ./build/${file}.js > ./dist/${file}.js`);
    shell.exec(`minify ./dist/${file}.js > ./dist/${file}.min.js`);
});
