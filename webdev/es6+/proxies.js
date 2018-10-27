import {equal, ok, throws} from "assert"

// The Proxy object is used to define custom behavior for fundamental operations e.g. property lookup, assignment, function invocation
// Proxy can modify behavior of the target object whenever its properties are accessed.

// A Proxy is parametrized with a target object and handler which defines traps and their behavior.
// When provided an empty handler proxies don't do much:
let target = {
    calc: function (param) {
        return param * this._state + this._zero();
    },
    _state: 10,
    _zero: () => 0
};

let proxy1 = new Proxy(target, {});
ok(proxy1._state === 10 && proxy1.calc(2) === 20 && proxy1._zero() === 0);

// Traps make it possible to intercept object property access both for reads(get trap) and writes(set trap).
// There are many more traps available: has, apply, construct, deleteProperty, ...
let proxy2 = new Proxy(target, {
    get(target, key) {
        console.log(`[GET TRAP] key: ${key}, target: ${JSON.stringify(target)}`);
        return target[key];
    },
    set(target, key, value) {
        console.log(`[SET TRAP] key: ${key}, value: ${value}, target: ${JSON.stringify(target)}`);
        target[key] = value;
        return true;
    }
});

proxy2._state = 20;
ok(proxy2._state === 20 && proxy2.calc(2) === 40 && proxy2._zero() === 0);

// A proxy that prevents access to a 'private' object members
function preventPrivateAccess(target) {
    function failWhenPropertyIsPrivate(key) {
        if (key.startsWith("_")) {
            throw new Error(`access to private member: ${key}`);
        }
    }

    return new Proxy(target, {
        get(target, key) {
            const property = target[key];
            failWhenPropertyIsPrivate(key);

            if (typeof property === "function") {
                return (...args) => property.apply(target, args)
            }
            else return property;
        },
        set(target, key, value) {
            failWhenPropertyIsPrivate(key);
            target[key] = value;
            return true;
        }
    });
}
// Access to the private members results in an Error
let proxy3 = preventPrivateAccess(target);
throws(() => {proxy3._state = 20});
throws(() => {let value = proxy3._state});
throws(() => {proxy3._zero()});

// But public functions should still have access to private members
equal(200, proxy3.calc(10));
