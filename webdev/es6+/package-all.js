// Package and minify all javascript files
const shell = require('shelljs');
const fileSystem = require('fs');
const baseDir = process.cwd(), buildDir = `${baseDir}/build`, distDir = `${baseDir}/dist`;

const jsFiles = (file) => file.endsWith('.js');

fileSystem.readdirSync(buildDir).filter(jsFiles).forEach(bundle);

function bundle(js) {
    let minJs = js.replace('.js', '.min.js');

    shell.exec(`browserify ${buildDir}/${js} > ${distDir}/${js}`);
    shell.exec(`minify ${distDir}/${js} > ${distDir}/${minJs}`);
}
