import "@babel/polyfill"
import {deepEqual, equal, notEqual, ok} from "assert"


// map() creates a new array with the results of calling a provided function on every element
deepEqual([4, 2, 11], ['this', 'is', 'interesting'].map(word => word.length));

// filter() creates a new array with all elements that pass test implemented by the provided function
deepEqual([10, 11], [1, 2, 3, 4, 6, 9, 10, 11].filter(e => e >= 10));

// reduce() applies a function against an accumulator and each element in the array to reduce it to a single value
equal(21, [0, 1, 2, 3, 4, 5, 6].reduce((sum, value) => sum + value, 0));
deepEqual([1, 2, 3, 4, 5, 6], [[1], [2, 3, 4], [5, 6]].reduce((array, item) => array.concat(item)), []);

// flatMap() is similar to map but each element can be mapped to multiple elements
Array.prototype.flatMap = function (f) {
    return this.reduce((list, value, idx) => list.concat(f(value, idx)), []);
};

deepEqual(['never', 'underestimate', 'the', 'power', 'of', 'stupid', 'people', 'in', 'large', 'groups'],
    ["never underestimate the power of stupid", "people in large", "groups"].flatMap(e => e.split(" ")));

// every() determines whether all members of an array satisfies the specified test
ok(['cpu', 'motherboard', 'memory', 'computer'].every(e => e.length > 2));

// some() determines whether the specified callback returns true for any member of an array
ok([1, 10, 15, 100].some(e => e === 15));

// zip()
Array.prototype.zip = function (that) {
    const length = Math.min(this.length, that.length);
    return this.flatMap((item, idx) => {
        if (idx < length) return [[item, that[idx]]];
        else return [];
    });
};

deepEqual([], [].zip([]));
deepEqual([], [1, 2, 3].zip([]));
deepEqual([[1, {a: '1', b: '2', c: '3'}]], [1, 2, 3, 5, 6].zip([{a: '1', b: '2', c: '3'}]));
deepEqual([[1, 3]], [1, 2].zip([3]));
deepEqual([[1, 'a'], [2, 'b'], [3, 'c'], [4, 'd'], [5, 'e']], [1, 2, 3, 4, 5].zip(['a', 'b', 'c', 'd', 'e']));
