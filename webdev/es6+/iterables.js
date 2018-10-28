import "@babel/polyfill"
import {deepEqual} from "assert"
import "./lib/extensions/arrays";

/*
    The iterable protocol allows JavaScript objects to define or customize their iteration behavior.
    ES6 provides many iterable data structures: Array, Map and even String.
    In order to be iterable, an object must implement '@@iterator' method which is defined via constant Symbol.iterator
 */
const iterable1 = {
    [Symbol.iterator]() {
        const values = Array.clone(this.values);

        return {
            next() {
                return {
                    done: values.isEmpty(),
                    value: values.shift()
                }
            }
        }
    },
    values: [1, 10, 100, 1000]
};

deepEqual([1, 10, 100, 1000], Array.from(iterable1));


// Generator function is a convenient way to implement iterable object.
const iterable2 = {
    * [Symbol.iterator]() {
        const values = Array.clone(this.values);

        while (values.isNotEmpty()) {
            yield values.shift();
        }
    },
    values: [1, 10, 100, 1000]
};

deepEqual([1, 10, 100, 1000], Array.from(iterable2));


// It's also possible to extend existing types to support iteration. In the following example we iterate over digits in a Number.
// The rests of modulo division are kept on the call stack and later returned in a reverse order.
Number.prototype[Symbol.iterator] = function* (arg) {
    let value = arg || this;

    if (value >= 0) {
        let rest = value % 10;

        let next = Math.floor(value / 10);
        if (next !== 0) {
            yield* this[Symbol.iterator](next);
        }
        yield rest;
    }
    else {
        yield '-';
        yield* this[Symbol.iterator](-value);
    }
};

deepEqual(['-', 1], Array.from(-1));
deepEqual(['-', 1, 1, 2, 3], Array.from(-1123));
deepEqual([0], Array.from(0));
deepEqual([4], Array.from(4));
deepEqual([9], Array.from(9));
deepEqual([1, 0], Array.from(10));
deepEqual([1, 2, 5, 7], Array.from(1257));
deepEqual([1, 2, 3, 1, 2, 3, 2, 1, 3, 1, 5, 2, 1, 4, 5], Array.from(123123213152145));
