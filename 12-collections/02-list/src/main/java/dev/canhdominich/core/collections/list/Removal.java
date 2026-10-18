package dev.canhdominich.core.collections.list;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

public final class Removal {

    private Removal() {
    }

    private static List<Integer> numbers() {
        return new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
    }

    public static String removeInsideForEach() {
        List<Integer> numbers = numbers();
        try {
            for (Integer n : numbers) {
                if (n % 2 == 0) {
                    numbers.remove(n);
                }
            }
            return "no exception " + numbers;
        } catch (ConcurrentModificationException e) {
            return "ConcurrentModificationException with message " + e.getMessage() + ", the list is now " + numbers;
        }
    }

    public static String removeTheSecondToLastInsideForEach() {
        List<Integer> numbers = new ArrayList<>(List.of(1, 2, 3));
        try {
            for (Integer n : numbers) {
                if (n == 2) {
                    numbers.remove(n);
                }
            }
            return "no exception, the list is " + numbers + " (hasNext() sees the new size and stops, so the check is never reached)";
        } catch (ConcurrentModificationException e) {
            return "ConcurrentModificationException";
        }
    }

    public static String removeByIndexLoopSkipsElements() {
        List<Integer> numbers = new ArrayList<>(List.of(2, 4, 6, 7));
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) % 2 == 0) {
                numbers.remove(i);
            }
        }
        return "forward index loop left " + numbers + " because elements shift left after each removal";
    }

    public static String removeWithIterator() {
        List<Integer> numbers = numbers();
        Iterator<Integer> it = numbers.iterator();
        while (it.hasNext()) {
            if (it.next() % 2 == 0) {
                it.remove();
            }
        }
        return "Iterator.remove gives " + numbers;
    }

    public static String removeIf() {
        List<Integer> numbers = numbers();
        boolean changed = numbers.removeIf(n -> n % 2 == 0);
        return "removeIf gives " + numbers + " and reports changed " + changed;
    }

    public static String removeBackwards() {
        List<Integer> numbers = numbers();
        for (int i = numbers.size() - 1; i >= 0; i--) {
            if (numbers.get(i) % 2 == 0) {
                numbers.remove(i);
            }
        }
        return "backward index loop gives " + numbers;
    }

    public static String copyThenRemove() {
        List<Integer> numbers = numbers();
        for (Integer n : new ArrayList<>(numbers)) {
            if (n % 2 == 0) {
                numbers.remove(n);
            }
        }
        return "iterating over a copy gives " + numbers;
    }
}
