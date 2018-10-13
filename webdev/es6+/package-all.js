// Package and minify all javascript files
const shell = require('shelljs');
const fileSystem = require('fs');

const jsFiles = (file) => file.endsWith('.js') && file !== 'package-all.js';
const currentDir = process.cwd();

fileSystem.readdirSync(currentDir).filter(jsFiles).forEach(bundle);

function bundle(jsFile) {
    let minJsFile = jsFile.replace('.js', '.min.js');

    shell.exec(`browserify ./build/${jsFile} > ./dist/${jsFile}`);
    shell.exec(`minify ./dist/${jsFile} > ./dist/${minJsFile}`);
}
