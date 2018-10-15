import {ok, deepEqual, equal} from "assert"
import {log, delayed} from "./lib/utils"
import './lib/extensions/sets'

// Maps hold key-value pairs, support fast lookup, convenient iteration and various utility methods (size, clear, ...)
let map = new Map();
map.set('doughnut', '🍩');
map.set('ice cream', '🍦');
equal('🍩', map.get('doughnut'));
equal(2, map.size);
deepEqual(['🍩', '🍦'], Array.from(map.values()));
deepEqual(['doughnut', 'ice cream'], Array.from(map.keys()));
deepEqual([['doughnut', '🍩'], ['ice cream', '🍦']], Array.from(map.entries()));

for (let [key, value] of map) {
    log(`${key} - ${value}`);
}

// Sets are used to store unique values and support very efficient membership testing.
let set1 = new Set([1, 2, 3, 4]);
let set2 = new Set([3, 4, 5, 6]);
let set3 = new Set(['🍪', '🍦', '🍧', '🍩']);

ok(set3.has('🍪'));
ok(!set3.has('?'));

deepEqual([0, 1, 2, 3], Array.from(new Set([0, 1, 2, 0, 1, 2, 3])));
deepEqual(new Set([1, 2, 3, 4, 5, 6, '🍪', '🍦', '🍧', '🍩']), set1.union(set2).union(set3));
deepEqual(new Set([3, 4]), set1.intersect(set2));
deepEqual(new Set([1, 2]), set1.diff(set2));

// A weak map is a map which keys are weakly referenced. An entry is removed from the map when there are no more
// strong references to the key object. Supports only has(), get(), set() and delete()
let key1 = {id: 1}, key2 = {id: 1};
let weakMap = new WeakMap();
weakMap.set(key1, 'value1');
weakMap.set(key2, 'value2');

equal('value1', weakMap.get(key1));
equal('value2', weakMap.get(key2));


// A weak set is a set which elements are weakly referenced. An element is removed from the set when there are no more
// strong references to it. Set Supports only has(), add() and delete()
let element1 = {id: 1}, element2 = {id: 2};
let weakSet = new WeakSet([element1, element2]);

ok(weakSet.has(element1));
ok(weakSet.has(element2));