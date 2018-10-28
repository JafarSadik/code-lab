import "@babel/polyfill"
import {deepEqual} from "assert"
import {isPrime} from "./lib/math";
import "./lib/extensions/arrays";

/*
    Generator functions allow to define an iterative algorithm by writing a function whose execution is not continuous.

    Generator functions are declared with 'function*'. They return next values with the use of 'yield' which interrupts
    its execution until next() method is called. A generator function can call itself or other generators with 'yield*'.
    Generator execution can be terminated by 'return', error or reaching the end of function.
 */

function* seq1() {
    yield 1;
    yield 2;
    yield 3;
    yield* seq2();
}

function* seq2() {
    yield 4;
    yield 5;
    yield 6;
    yield* seq1();
}

deepEqual([1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4], Array.firstFrom(seq1(), 16));

function* range(index, end) {
    yield index;
    if (index < end) {
        yield* range(index + 1, end);
    }
}

deepEqual([0, 1, 2, 3, 4, 5], Array.from(range(0, 5)));

// Prime number generator
function* primes(from = 2, to = Number.MAX_VALUE) {
    let current = from;

    while (current < to) {
        if (isPrime(current)) {
            yield current;
        }
        current++;
    }
}

const infinitePrimeSequence = primes();
deepEqual([2, 3, 5, 7, 11, 13], Array.firstFrom(infinitePrimeSequence, 6));

const boundedPrimeSequence = primes(2, 100);
deepEqual([2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97],
    Array.from(boundedPrimeSequence));

// Fibonacci sequence generator
function* fibonacci(max = Number.MAX_VALUE) {
    let fn1 = 0, fn2 = 0, curr = 0;

    function resetSequence() {
        [fn1, fn2, curr] = [0, 1, 0]
    }

    resetSequence();

    while (true) {
        [curr, fn1, fn2] = [fn1, fn2, fn1 + fn2];

        if (curr > max) {
            return;
        }
        else if (yield curr) {
            resetSequence();
        }
    }
}

// Get the whole Fibonacci sequence bounded by 50
deepEqual([0, 1, 1, 2, 3, 5, 8, 13, 21, 34], Array.from(fibonacci(50)));

// Get first 30 numbers from an infinite Fibonacci sequence
deepEqual([0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711,
    28657, 46368, 75025, 121393, 196418, 317811, 514229], Array.firstFrom(fibonacci(), 30));

// The next() method can pass a parameter to the generator.
// In this case we are able to reset the sequence.
let fibSeq = fibonacci();
let next = (param) => fibSeq.next(param).value;
deepEqual([0, 1, 1, 2, 0, 1, 1, 2, 3], [next(), next(), next(), next(), next(true), next(), next(), next(), next()]);


// Generators can be defined as object's methods
let obj = {
    * seq1() {
        yield 1;
        yield 2;
        yield 3;
        yield* this.seq2()
    },
    * seq2() {
        yield 4;
        yield 5;
    }
};

deepEqual([1, 2, 3, 4, 5], Array.from(obj.seq1()));
deepEqual([4, 5], Array.from(obj.seq2()));