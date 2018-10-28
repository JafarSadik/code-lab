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