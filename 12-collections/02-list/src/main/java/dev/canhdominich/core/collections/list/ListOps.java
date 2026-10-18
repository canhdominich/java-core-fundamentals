package dev.canhdominich.core.collections.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public final class ListOps {

    private ListOps() {
    }

    public static String basics() {
        List<String> names = new ArrayList<>();
        names.add("An");
        names.add("Chi");
        names.add(1, "Binh");
        String replaced = names.set(0, "Anh");
        return "after add, insert and set " + names + ", set returned the old value " + replaced + ", indexOf(Chi) " + names.indexOf("Chi")
                + ", indexOf(Zed) " + names.indexOf("Zed") + ", contains(Binh) " + names.contains("Binh") + ", size " + names.size();
    }

    public static String removeByIndexOrByValue() {
        List<Integer> numbers = new ArrayList<>(List.of(10, 20, 30, 1));
        numbers.remove(1);
        String byIndex = numbers.toString();
        numbers.remove(Integer.valueOf(1));
        return "remove(1) removes the element at index 1: " + byIndex + ", remove(Integer.valueOf(1)) removes the value 1: " + numbers;
    }

    public static String outOfRange() {
        List<String> names = new ArrayList<>(List.of("a", "b"));
        try {
            names.get(2);
            return "no exception";
        } catch (IndexOutOfBoundsException e) {
            return e.getClass().getSimpleName() + ": " + e.getMessage();
        }
    }

    public static String listEqualsComparesElementsInOrder() {
        List<Integer> array = new ArrayList<>(List.of(1, 2, 3));
        List<Integer> linked = new LinkedList<>(List.of(1, 2, 3));
        List<Integer> immutable = List.of(1, 2, 3);
        List<Integer> other = new ArrayList<>(List.of(3, 2, 1));
        return "ArrayList equals LinkedList " + array.equals(linked) + ", equals List.of " + array.equals(immutable) + ", different order " + array.equals(other);
    }

    public static String subListIsAView() {
        List<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        List<Integer> middle = numbers.subList(1, 4);
        middle.set(0, 99);
        String afterSet = numbers.toString();
        numbers.add(6);
        String result;
        try {
            middle.size();
            result = "no exception";
        } catch (java.util.ConcurrentModificationException e) {
            result = "ConcurrentModificationException";
        }
        return "writing through the view changed the parent: " + afterSet + ", structural change of the parent then using the view: " + result;
    }

    public static String listIteratorWorksBackwards() {
        List<String> names = new ArrayList<>(List.of("a", "b", "c"));
        ListIterator<String> it = names.listIterator(names.size());
        StringBuilder out = new StringBuilder();
        while (it.hasPrevious()) {
            out.append(it.previous());
        }
        return "backwards " + out;
    }

    public static String fixedSizeAndImmutableLists() {
        List<String> viaAsList = java.util.Arrays.asList("a", "b");
        viaAsList.set(0, "z");
        String set = viaAsList.toString();
        String add;
        try {
            viaAsList.add("c");
            add = "added";
        } catch (UnsupportedOperationException e) {
            add = "UnsupportedOperationException";
        }
        return "Arrays.asList set works " + set + ", add: " + add;
    }
}
