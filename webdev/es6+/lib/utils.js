// Executes callback after a given delay
export function delayed(fun, delay = 100) {
    setTimeout(() => fun(), delay);
}

// Executes a callback when the condition is fulfilled
export function when(predicate) {
    function thenExecute(fun) {
        if (predicate()) fun();
        else delayed(() => thenExecute(fun), 100);
    }
    return {thenExecute}
}

// Abbreviation for console.log()
export function log(message) {
    console.log(message);
}
