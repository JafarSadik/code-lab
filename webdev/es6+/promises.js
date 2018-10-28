import {delayed} from './lib/utils'

// The Promise object represent the eventual completion (or failure) of an asynchronous operation.
// Promises have replaced callback functions as a preferred programming style for handling asynchronous calls.

// Basic example
let promise1 = new Promise(
    (resolve, reject) => setTimeout(() => resolve(':)', 1000))
);

promise1.then((value) => console.log(`[1] Basic promise evaluated ${value}`));

// It's very easy to run a sequence of operations on promise completion.
// Moreover, a promise can be returned to another promise creating a chain of promises.
// When anything fails, the control goes to the nearest catch() statement down the chain.
compute(15)
    .then((value) => console.log(`[2] compute(15) = ${value}`))
    .then(() => console.log('[3] running the next computation ...'))
    .then(() => compute(2000))
    .then((value) => console.log(`[4] compute(2000) = ${value}`))
    .catch(err => console.log('[5] Something went wrong: ' + err));


// Promise.all() creates a promise that waits for a completion of all provided promises.
Promise.all([compute(10), compute(100)]).then(values => console.log(`[6] ${values}`));

// It's also possible to provide empty list or mix values of different types
Promise.all([]).then(values => console.log(`[7] ${values}`));
Promise.all([compute(10), compute(100), "string", 60000])
    .then(values => console.log(`[8] ${values}`));

// Use a promise with a destructive assignment
Promise.all([compute(10), compute(100), "string", 60000])
    .then(([r1, r2, r3, r4]) => console.log(`[9] r1 = ${r1}, r2 = ${r2}, r3 = ${r3}, r4 = ${r4}`));

// Create a resolved or a rejected promise
Promise.resolve(15).then(value => console.log(`[10] Promise.resolve(15) = ${value}`));
Promise.reject('Promise.reject() works as expected...').catch(reason => console.log(`[11] ${reason}`));

// Synchronous method that returns a Promise.
function compute(result) {
    const delay = 3000;

    return new Promise((resolve, reject) =>
        delayed(() => {
                if (result < 1000) resolve(result);
                else reject(':(');
            }
            , delay
        )
    )
}