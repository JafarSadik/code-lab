// Request library is a simple http client
import request from 'request'
import "@babel/polyfill"

let options = {
    url: 'https://api.github.com/repos/jafarsadik/code-lab',
    headers: {
        'User-Agent': 'request',
        'Content-Type': 'application/json'
    }
};

let callback = (error, response, body) => console.log(JSON.parse(body));

request(options, callback);
