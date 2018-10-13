import * as http from 'http'

const port = process.env.PORT || 8080;

http.OutgoingMessage.prototype.writeJson = function (json) {
    this.writeHead(200, {'Content-Type': 'application/json'});
    this.write(JSON.stringify(json, null, '\t'));
    this.end();
};

http.createServer((req, res) => res.writeJson(
    {
        message: 'Hello world!',
        context: {
            host: req.headers.host,
            url: req.url,
            headers: req.headers
        }
    }
)).listen(port);
