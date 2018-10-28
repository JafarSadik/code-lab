// Retrieves first elements from an iterable collection.
// Retrieves first elements from an iterable collection.
Array.firstFrom = (iterable, size) => {
    const array = [];
    for (let item of iterable) {
        if (size-- > 0) array.push(item);
        else break;
    }
    return array;
};

Array.clone = (array) => array.slice();

Array.prototype.isEmpty = function () {
    return this.length === 0;
};

Array.prototype.isNotEmpty = function () {
    return !this.isEmpty();
};