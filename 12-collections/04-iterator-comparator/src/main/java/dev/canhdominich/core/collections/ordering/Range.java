package dev.canhdominich.core.collections.ordering;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Range implements Iterable<Integer> {

    private final int from;
    private final int toExclusive;

    public Range(int from, int toExclusive) {
        this.from = from;
        this.toExclusive = toExclusive;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            private int next = from;

            @Override
            public boolean hasNext() {
                return next < toExclusive;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("range exhausted at " + toExclusive);
                }
                return next++;
            }
        };
    }
}
