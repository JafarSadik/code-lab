// Importing node modules installed in node_modules directory by name, no path required
import "@babel/polyfill"
import {deepEqual, equal, notEqual, ok} from "assert"
// Importing custom modules requires relative paths such as ./lib, ../lib or ../../lib
// There are two ways to import named exports from a module
import {mult, pi} from "./lib/math";
import {log, when} from "./lib/utils"
// ... and one way to import a default export from a module
import messages from "./lib/messages"

equal('2π = 6.283184', `2π = ${mult(2, pi)}`);
ok(messages.wrongPassword !== undefined && messages.requiresAdminRole !== undefined);
when(() => Math.random() > 0.95).thenExecute(() => console.log("done"));

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
    () => log('function 1'),
    () => log('function 2'),
    () => log('function 3')
].forEach(fn => fn());

let adder = (a, b) => a + b;
equal('2 + 3 = 5', `2 + 3 = ${adder(2, 3)}`);

deepEqual([2, 4, 6, 8, 10], [1, 2, 3, 4, 5].map((v) => v * 2));

// New Array functions
deepEqual([15, 20, 30], Array.of(15, 20, 30));
deepEqual(['a', 'b', 'c'], Array.from('abc'));
deepEqual([15, 20, 30], Array.from([15, 20, 30]));
deepEqual([20, 40, 60], Array.from([10, 20, 30], x => x * 2));
deepEqual([1, 2, 3], Array.from(new Set([1, 2, 3, 1, 2, 3, 1, 2, 3])));
deepEqual([1, 1, 0, 0, 0, 0], [1, 1, 1, 1, 1, 1].fill(0, 2));
deepEqual({type: 'c', weight: 5}, [{type: 'a', weight: 1}, {type: 'b', weight: 10}, {type: 'c', weight: 5}]
    .find(item => item.type === 'c'));
equal(1, [{type: 'a', weight: 1}, {type: 'b', weight: 10}].findIndex(item => item.type === 'b'));
deepEqual([2, 2, 1, 1, 1, 2, 2], [1, 1, 1, 1, 1, 2, 2].copyWithin(0, 5, 7));
deepEqual([[0, 'a'], [1, 'b']], Array.from(['a', 'b'].entries()));
// shift() removes first element from an array and returns this element
array = [1, 2, 3, 4];
ok(1, array.shift());
deepEqual([2, 3, 4], array);
// unshift() adds one or more elements to the beginning of an array and returns the length of the array
equal(6, array.unshift(-1, 0, 1));
deepEqual([-1, 0, 1, 2, 3, 4], array);

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

equal('Jafar Sadik <dzafar.sadik@gmail.com> is a software developer', `${user} <${email}> is ${getRole()}`);

log(`
       1
     2 1 2
    5 3 3 5
    `);

// Default and extended parameters handling
function sum(first = 0, ...numbers) {
    return first + numbers.reduce((acc, val) => acc + val, 0);
}

equal('sum() = 0', `sum() = ${sum()}`);
equal('sum(1,2,3) = 6', `sum(1,2,3) = ${sum(1, 2, 3)}`);

// Spread operator converts an iterable collection into parameter list
const numbers = [1, 2, 3, 4, 5, 6, 7];
equal('max([1..7]) = 7', `max([1..7]) = ${Math.max(...numbers)}`);

// Property shorthand
var x = 15, y = 14;
let pos = {x, y};  // instead of {x: x, y: y}
log(pos);

// The destructing assignment syntax is JS expression that makes it possible to unpack values from arrays or object into distinct variables.
// Array decomposition with destructing assignment
var [v1, v2, v3] = [11, 12, 13];
var [v1, v2, v3, v4 = 0] = [11, 12, 13]; // with default value
var [v1, ...rest] = [11, 12, 13]; // with extended arguments list

// Object decomposition with destructing assignment
var {op, lhs, rhs} = {'op': '*', 'lhs': 15, 'rhs': 100};
equal('15 * 100', `${lhs} ${op} ${rhs}`);

// Deep object decomposition with destructing assignment
var {op: op1, lhs: {op: op2}} = {'op': '*', 'lhs': {'op': '+', 'lhs': 1, 'rhs': 2}, 'rhs': 100};
ok(op1 === '*' && op2 === '+');

// Object and array matching
var obj = {A: 1};
var list = [1];
var {A, B = 2} = obj;
var [X, Y = 2] = list;

// Symbol type can be used to define unique values where otherwise you would use string or number
notEqual(Symbol('key'), Symbol('key'));

const logLevels = {
    DEBUG: Symbol('debug'),
    INFO: Symbol('info'),
    WARN: Symbol('warn')
};

log(logLevels.INFO, 'info message');

// Regular expressions support groups
let dateRegexp = /([0-9]{4})-([0-9]{2})-([0-9]{2})/;
let [year, month, day] = dateRegexp.exec('2018-11-30').slice(1).map(e => parseInt(e));
ok(year === 2018 && month === 11 && day === 30);

// package.json can be easily used by build script
import project from '../package.json'
console.log(`Project ${project.name} ${project.version}`);
