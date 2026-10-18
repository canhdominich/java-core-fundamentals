package dev.canhdominich.core.oop.inheritance;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CountingSet<E> {

    private final Set<E> inner = new HashSet<>();
    private int attempts;

    public boolean add(E element) {
        attempts++;
        return inner.add(element);
    }

    public boolean addAll(Collection<? extends E> elements) {
        boolean changed = false;
        for (E element : elements) {
            changed |= add(element);
        }
        return changed;
    }

    public int attempts() {
        return attempts;
    }

    public int size() {
        return inner.size();
    }
}
