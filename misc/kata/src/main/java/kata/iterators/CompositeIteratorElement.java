package kata.iterators;

import java.util.Iterator;

/**
 * A {@link Comparable} wrapper for a single {@link Iterator}.
 * It caches the recently accessed item as {@code Iterator.next()} can be read only once.
 */
class CompositeIteratorElement<T extends Comparable<T>> implements Comparable<CompositeIteratorElement<T>> {
    final Iterator<T> iterator;
    final T value;

    CompositeIteratorElement(Iterator<T> iterator) {
        this.iterator = iterator;
        this.value = iterator.next();
    }

    @Override
    public int compareTo(CompositeIteratorElement<T> that) {
        return value.compareTo(that.value);
    }
}