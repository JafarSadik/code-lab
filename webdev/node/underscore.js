import _ from 'underscore'
import {deepEqual, equal, ok} from "assert"


// COLLECTION FUNCTIONS
// each iterates over a list of elements
let result = [];
_.each([1, 2, 3], it => result.push(it));
deepEqual([1, 2, 3], result);

// map produces a new array of values by mapping each value in list through transformation function
deepEqual([1, 4, 9], _.map([1, 2, 3], it => it * it));

// reduce transforms a list of values to a single value
equal(6, _.reduce([1, 2, 3], (acc, elem) => acc + elem));

// find iterates through a list returning the first element matching the predicate
equal('is', _.find(['every', 'time', 'is', 'different'], e => e.length < 3));

// filter returns a new array containing all matching elements
deepEqual([5, 6, 7], _.filter(_.range(1, 8), e => e >= 5));

// some returns true if any of the values in the list match
ok(_.some([1, 2, 3], e => e % 2 === 0));

// every returns true if all values in the list pass the predicate
ok(_.every([1, 2, 3], e => e > 0));

// contains returns true if a value exists
ok(_.contains([1, 2, 3], 3));

// sortBy returns a sorted copy of list
deepEqual([1, 2, 3, 4, 5], _.sortBy([5, 4, 3, 2, 1]));
deepEqual([4, 2, 1, 3, 5], _.sortBy([1, 2, 3, 4, 5], e => {
    if (e % 2 === 0) return e * -1; else return e;
}));
deepEqual([{title: 'daily mail', subscribers: 100000}, {title: 'guardian', subscribers: 13200}],
    _.sortBy([{title: 'guardian', subscribers: 13200}, {title: 'daily mail', subscribers: 100000}], 'title'));

// indexBy returns an object with an index of each item
deepEqual(
    {
        "40": {name: 'moe', age: 40},
        "50": {name: 'larry', age: 50},
        "60": {name: 'curly', age: 60}
    },
    _.indexBy([{name: 'moe', age: 40}, {name: 'larry', age: 50}, {name: 'curly', age: 60}], 'age'));

// ARRAY FUNCTIONS
// flatten flattens a nested array
deepEqual([1, 2, 3, 4, 5], _.flatten([[1, 2], [3, 4, 5]]));

// union
deepEqual([1, 2, 3, 101, 10], _.union([1, 2, 3], [101, 2, 1, 10], [2, 1]));

// intersection
deepEqual([1, 2], _.intersection([1, 2, 3], [101, 2, 1, 10], [2, 1]));

// difference
deepEqual([1, 3, 4], _.difference([1, 2, 3, 4, 5], [5, 2, 10]));

// zip merges together the values of the arrays
deepEqual([["moe", 30, true], ["larry", 40, false], ["curly", 50, false]],
    _.zip(['moe', 'larry', 'curly'], [30, 40, 50], [true, false, false]));

// range([start], stop, [step]) creates a list of integers
deepEqual([0, 1, 2, 3], _.range(4));
deepEqual([2, 3, 4, 5], _.range(2, 6));
deepEqual([2, 4, 6, 8], _.range(2, 10, 2));
deepEqual([0, -1, -2, -3], _.range(0, -4, -1));

// FUNCTION FUNCTIONS
// partial partially applies a function by filling in any number of its arguments
let subtract = (a, b) => b - a;
let sub5 = _.partial(subtract, 5);
equal(15, sub5(20));

let subFrom20 = _.partial(subtract, _, 20);
equal(5, subFrom20(15));

// delay invokes a function after wait milliseconds
_.delay(() => console.log('done'), 1000);

// defer defers invoking the function until the current stack has cleared, similar to using setTimeout with a delay of 0
_.defer(() => console.log('deferred computation'));

// wrap wraps the first function inside of the wrapper function
let originalFunction = () => [1, 2, 3, 4, 5];
let wrappedFunction = _.wrap(originalFunction, (f) => f().filter(e => e % 2 === 0));
deepEqual([2, 4], wrappedFunction());

// compose returns a composition of a list of functions, where each function consumes the return value of the function that follows
let trim = (text) => text.trim();
let length = (text) => text.length;
equal(4, _.compose(length, trim)('  abcd   '));

// OBJECT FUNCTIONS
// keys retrieves all the names of the object's own enumerable properties
deepEqual(['one', 'two', 'three'], _.keys({one: 1, two: 2, three: 3}));

// values returns all the values of object's own properties
deepEqual([1, 2, 3], _.values({one: 1, two: 2, three: 3}));

// mapObject transforms each property
deepEqual({speed: 6, acceleration: 1}, _.mapObject({speed: 5, acceleration: 0}, (value, key) => value + 1));

// pairs converts an object into a list of [key, value] pairs
deepEqual([['speed', 6], ['acceleration', 1]], _.pairs({speed: 6, acceleration: 1}));

// extend(destination, *sources) shallowly copies all properties in the source objects to the destination object
let object = _.extend({name: 'moe'}, {age: 50},
    {
        toString: function () {
            return `${this.name} is ${this.age} years old`
        }
    });
equal('moe', object.name);
equal(50, object.age);
equal('moe is 50 years old', object.toString());

// functions returns sorted list of the names of every method in an object
console.log(_.functions(_));

// isEqual performs a deep comparison between two objects
ok(_.isEqual({name: 'moe', luckyNumbers: [13, 27, 34]}, {name: 'moe', luckyNumbers: [13, 27, 34]}));

// isObject returns true if value is an Object
ok(_.isObject({}));

// isFunction returns true if object is a Function
ok(_.isFunction(() => {
}));

// isString returns true if object is a String
ok(_.isString('abc'));

// isNumber returns true if object is a Number
ok(_.isNumber(5));

// isBoolean returns true if object is a Boolean
ok(_.isBoolean(true));
