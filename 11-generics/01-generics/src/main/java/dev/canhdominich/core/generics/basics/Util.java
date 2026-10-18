package dev.canhdominich.core.generics.basics;

import java.util.List;

public final class Util {

    private Util() {
    }

    public static <T> T first(List<T> items) {
        if (items.isEmpty()) {
            throw new IllegalArgumentException("list is empty");
        }
        return items.get(0);
    }

    public static <T extends Comparable<T>> T max(List<T> items) {
        T best = items.get(0);
        for (T item : items) {
            if (item.compareTo(best) > 0) {
                best = item;
            }
        }
        return best;
    }

    @SafeVarargs
    public static <T> List<T> listOf(T... items) {
        return java.util.Arrays.asList(items);
    }

    public static <K, V> Pair<K, V> pair(K key, V value) {
        return new Pair<>(key, value);
    }
}
