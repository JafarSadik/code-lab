// Named exports allow multiple exports per module
// usage: import * as math from './lib/math'
//        import {mult, pi} from './lib/math'
export function mult(a, b) {
    return a * b;
}

export const pi = 3.141592;

export function isPrime(number) {
    if (number <= 1) return false;
    const limit = Math.floor(Math.sqrt(number));

    for (let i = 2; i <= limit; i++) {
        if (number % i === 0) return false;
    }
    return true;
}