package dev.canhdominich.core.generics.wildcards;

import java.util.List;

public final class Bounds {

    private Bounds() {
    }

    public static <T extends Number> double sum(List<T> numbers) {
        double total = 0;
        for (T number : numbers) {
            total += number.doubleValue();
        }
        return total;
    }

    public static <T extends Number & Comparable<T>> T largest(List<T> numbers) {
        T best = numbers.get(0);
        for (T number : numbers) {
            if (number.compareTo(best) > 0) {
                best = number;
            }
        }
        return best;
    }
}
