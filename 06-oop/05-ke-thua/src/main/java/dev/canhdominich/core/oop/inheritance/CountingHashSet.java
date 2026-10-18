package dev.canhdominich.core.oop.inheritance;

import java.util.Collection;
import java.util.HashSet;

public class CountingHashSet<E> extends HashSet<E> {

    private int attempts;

    @Override
    public boolean add(E element) {
        attempts++;
        return super.add(element);
    }

    @Override
    public boolean addAll(Collection<? extends E> elements) {
        attempts += elements.size();
        return super.addAll(elements);
    }

    public int attempts() {
        return attempts;
    }
}
