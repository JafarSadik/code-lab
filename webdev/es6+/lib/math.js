// Named exports allow multiple exports per module
// usage: import * as math from './lib/math'
//        import {mult, pi} from './lib/math'
export function mult(a, b) {
    return a * b;
}

export const pi = 3.141592;