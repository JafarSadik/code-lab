import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import HelloWorld from './HelloWorld'

let rootElement = document.getElementById('root');

let page =
    <>
        <HelloWorld active message='Hello World !!!'/>
        <HelloWorld message='Hello World'/>
        <HelloWorld message=':D'/>
    </>;

ReactDOM.render(page, rootElement);

