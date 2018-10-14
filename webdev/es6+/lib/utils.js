// Exporting two functions
export function delayed(fun, delay = 100) {
    setTimeout(() => fun(), delay);
}

export function log(message) {
    console.log(message);
}
