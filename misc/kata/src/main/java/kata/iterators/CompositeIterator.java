package kata.iterators;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * A wrapper around Integer iterators. Allows ascending traversal through items in multiple iterators.
 * The class is not thread-safe.
 */
public class CompositeIterator<T extends Comparable<T>> implements Iterator<T> {
    // A priority queue maintains elements in an ascending order.
    private final PriorityQueue<IteratorElement> priorityQueue;
    // Allows to remove the current element and holds the next element for priority queue.
    private Iterator<T> iterator = emptyIterator();
    // Recent value returned by the iterator.
    private T currentValue;

    @SafeVarargs
    public CompositeIterator(Iterator<T>... iterators) {
        requireValidParameterList(iterators);
        priorityQueue = new PriorityQueue<>(firstElementFromEachIterator(iterators));
    }

    /**
     * Returns true if the iterator has more elements or false otherwise.
     */
    @Override
    public boolean hasNext() {
        return !priorityQueue.isEmpty() || iterator.hasNext();
    }

    /**
     * Returns the next element in the iteration.
     *
     * @throws IllegalArgumentException provided iterators are either not sorted or contain duplicates
     * @throws NoSuchElementException   if the iterator has no more elements
     */
    @Override
    public T next() {
        enqueueAvailableElement();
        IteratorElement next = getNextElementInOrder();
        ensureIteratorsSortedWithoutDuplicates(currentValue, next.value);
        iterator = next.iterator;
        currentValue = next.value;
        return currentValue;
    }

    /**
     * Removes from the underlying collection the last element returned by this iterator.
     *
     * @throws IllegalStateException remove() method called twice on the same element or next() method hasn't been called
     */
    @Override
    public void remove() {
        iterator.remove();
    }

    private void enqueueAvailableElement() {
        if (iterator.hasNext()) {
            priorityQueue.add(new IteratorElement(iterator));
        }
    }

    private IteratorElement getNextElementInOrder() {
        IteratorElement element = priorityQueue.poll();
        failWhenResultIndicatesEmptyIterator(element);
        return element;
    }

    private void failWhenResultIndicatesEmptyIterator(IteratorElement e) {
        if (e == null) {
            throw new NoSuchElementException("empty iterator");
        }
    }

    private void ensureIteratorsSortedWithoutDuplicates(T currentValue, T nextValue) {
        if (currentValue != null) {
            checkArgument(currentValue.compareTo(nextValue) < 0, "at least one iterator not sorted");
            checkArgument(currentValue.compareTo(nextValue) != 0, "duplicate items in one or more iterators");
        }
    }

    private List<IteratorElement> firstElementFromEachIterator(Iterator<T>[] iterators) {
        return Stream.of(iterators)
                .filter(nonEmptyIterators())
                .map(IteratorElement::new)
                .collect(Collectors.toList());
    }

    private Predicate<Iterator<T>> nonEmptyIterators() {
        return Iterator::hasNext;
    }

    private void requireValidParameterList(Iterator<T>[] iterators) {
        checkArgument(iterators != null, "null argument list");
        checkArgument(allNonNullIterators(iterators), "null iterator");
    }

    private boolean allNonNullIterators(Iterator<T>[] iterators) {
        return Stream.of(iterators).filter(Objects::isNull).count() == 0;
    }

    private Iterator<T> emptyIterator() {
        return new ArrayList<T>().iterator();
    }

    /**
     * Wrapper for {@link Iterator} that supports comparison and therefore can be used as an element of {@link PriorityQueue}.
     */
    private class IteratorElement implements Comparable<IteratorElement> {
        final Iterator<T> iterator;
        final T value;

        IteratorElement(Iterator<T> iterator) {
            this.iterator = iterator;
            this.value = iterator.next();
        }

        @Override
        public int compareTo(IteratorElement that) {
            return value.compareTo(that.value);
        }
    }
}
