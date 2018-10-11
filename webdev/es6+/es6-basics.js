import "@babel/polyfill"
import {notEqual, deepEqual, equal, ok} from "assert"

// Let is similar to var but is only accessible in the block level where it is defined
let a = 50;
let b = 100;
if (true) {
    let a = 60;
    var c = 10;
    equal(6, a / c);
    equal(10, b / c);
}
equal(10, c);
equal(50, a);

// Const once declared cannot be reassigned
const d = "Constant variable";
const languages = ['js', 'scala', 'java'];
languages.push('python');

// Arrow function has a shorten syntax than a function expression and can refer to `this` value of the enclosing context
[
    () => console.log('function 1'),
    () => console.log('function 2'),
    () => console.log('function 3')
].forEach(fn => fn());

let adder = (a, b) => a + b;
console.log(`2 + 3 = ${adder(2, 3)}`);

deepEqual([2, 4, 6, 8, 10], [1, 2, 3, 4, 5].map((v) => v * 2));

// Support for binary literals and unicode
equal(0b11, 3);
equal(2, '𝌆'.length);

// for ... of loop
let array = ["first element", "second element", "third element"];
for (let element of array) {
    ok(array.indexOf(element) !== -1);
}

// Template string literal supports interpolation and multiline text
let email = 'dzafar.sadik@gmail.com';
let user = 'Jafar Sadik';

function getRole() {
    return 'a software developer';
}

console.log(`${user} <${email}> is ${getRole()}`);

console.log(`
       1
     2 1 2
    5 3 3 5
    `);

// Default and extended parameters handling
function sum(first = 0, ...numbers) {
    return first + numbers.reduce((acc, val) => acc + val, 0);
}
console.log(`sum(1,2,3) = ${sum()}`);
console.log(`sum(1,2,3) = ${sum(1,2,3)}`);

// Spread operator converts an iterable collection into parameter list
const numbers = [1, 2, 3, 4, 5, 6, 7];
console.log(`max(numbers) = ${Math.max(...numbers)}`);

// Property shorthand
var x = 15, y = 14;
let pos = {x, y};  // instead of {x: x, y: y}
console.log('pos', pos);

// The destructing assignment syntax is JS expression that makes it possible to unpack values from arrays or object into distinct variables.
// Array decomposition with destructing assignment
var [v1, v2, v3] = [11, 12, 13];
var [v1, v2, v3, v4 = 0] = [11, 12, 13]; // with default value
var [v1, ...rest] = [11, 12, 13]; // with extended arguments list

// Object decomposition with destructing assignment
var {op, lhs, rhs} = {'op': '*', 'lhs': 15, 'rhs': 100};
console.log(`${lhs} ${op} ${rhs}`);

// Deep object decomposition with destructing assignment
var {op: op1, lhs: {op: op2}} = {'op': '*', 'lhs': {'op': '+', 'lhs': 1, 'rhs': 2}, 'rhs': 100};
ok(op1 === '*' && op2 === '+');

// Object and array matching
var obj = { A: 1 };
var list = [ 1 ];
var { A, B = 2 } = obj;
var [ X, Y = 2 ] = list;

// Symbol type can be used to define unique values where otherwise you would use string or number
notEqual(Symbol('key'), Symbol('key'));

const logLevels = {
    DEBUG: Symbol('debug'),
    INFO: Symbol('info'),
    WARN: Symbol('warn')
};

console.log(logLevels.INFO, 'info message');