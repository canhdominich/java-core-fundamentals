package dev.canhdominich.core.generics.wildcards;

import java.util.ArrayList;
import java.util.List;

public final class Wildcards {

    private Wildcards() {
    }

    public static double sumAll(List<? extends Number> numbers) {
        double total = 0;
        for (Number number : numbers) {
            total += number.doubleValue();
        }
        return total;
    }

    public static void fillWithIntegers(List<? super Integer> target, int count) {
        for (int i = 1; i <= count; i++) {
            target.add(i);
        }
    }

    public static <T> void copy(List<? super T> destination, List<? extends T> source) {
        for (T item : source) {
            destination.add(item);
        }
    }

    public static String describe(List<?> anything) {
        StringBuilder out = new StringBuilder("size " + anything.size() + ": ");
        for (Object item : anything) {
            out.append(item).append(' ');
        }
        return out.toString().trim();
    }

    public static String usage() {
        List<Integer> ints = List.of(1, 2, 3);
        List<Double> doubles = List.of(1.5, 2.5);
        List<Number> numbers = new ArrayList<>();
        List<Object> objects = new ArrayList<>();
        fillWithIntegers(numbers, 3);
        fillWithIntegers(objects, 2);
        List<Number> merged = new ArrayList<>();
        copy(merged, ints);
        copy(merged, doubles);
        return "sum of Integers " + sumAll(ints) + ", sum of Doubles " + sumAll(doubles) + ", numbers filled " + numbers + ", objects filled " + objects
                + ", merged " + merged + ", describe " + describe(merged);
    }
}
