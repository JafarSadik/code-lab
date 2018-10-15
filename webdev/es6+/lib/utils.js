// Executes callback after a given delay
export function delayed(fun, delay = 100) {
    setTimeout(() => fun(), delay);
}

// Abbreviation for console.log()
export function log(message) {
    console.log(message);
}
