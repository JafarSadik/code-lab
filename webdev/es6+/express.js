import express from 'express';
import {log} from "./lib/utils"
const router = express();
const port = process.env.PORT || 4000;

router.get('/', (req, res) => res.redirect('/helloworld'));

router.get('/helloworld', (req, res) => res.send('Hello World'));

router.get('/users/:username', (req, res) => res.send(`Hello ${req.params.username}`));

router.listen(port, () => log(`Server started on port ${port}`));