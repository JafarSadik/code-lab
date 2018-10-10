import express from 'express';
const router = express();
const port = process.env.PORT || 4000;

router.get('/', (req, res) => res.redirect('/helloworld'));

router.get('/helloworld', (req, res) => res.send('Hello World'));

router.get('/users/:username', (req, res) => res.send(`Hello ${req.params.username}`));

router.listen(port, () => console.log(`Server started on port ${port}`));