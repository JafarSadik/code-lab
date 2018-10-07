import "@babel/polyfill"
import {deepEqual, equal, ok} from "assert"

// Object values, keys and entries
let obj = {a: 1, b: 2, c: 2};
deepEqual([1, 2, 2], Object.values(obj));
deepEqual(['a', 'b', 'c'], Object.keys(obj));
deepEqual([['a', 1], ['b', 2], ['c', 2]], Object.entries(obj));

// String padding
equal('0001', '1'.padStart(4, 0));
equal('Asia      | 124', 'Asia'.padEnd(10, ' ') + '| 124');

// Exponential operator
equal(9, 3 ** 2);
equal(9, 81 ** 0.5);

// Trailing comma is allowed in array, object and function parameters
console.log([1, 2, 3,]);
console.log({a: 1, b: 2,});

// Array.prototype.includes can be used in place of indexOf
ok([1, 2, 3, 44].includes(3));
ok([1, 2, 3, 44].indexOf(3) !== -1);
