package dev.canhdominich.core.collections.ordering;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;

public final class Ordering {

    private Ordering() {
    }

    private static List<Student> students() {
        return new ArrayList<>(List.of(new Student("Chi", 80), new Student("An", 90), new Student("Binh", 80), new Student("Dung", 70)));
    }

    public static String customIterable() {
        int sum = 0;
        for (int value : new Range(1, 5)) {
            sum += value;
        }
        Iterator<Integer> it = new Range(0, 2).iterator();
        it.next();
        it.next();
        String exhausted;
        try {
            it.next();
            exhausted = "no exception";
        } catch (NoSuchElementException e) {
            exhausted = "NoSuchElementException: " + e.getMessage();
        }
        return "for-each over Range(1, 5) sums to " + sum + ", a third next() on Range(0, 2): " + exhausted;
    }

    public static String naturalOrder() {
        List<Student> list = students();
        Collections.sort(list);
        return "sorted by compareTo (score) " + list + ", stable for equal scores Chi before Binh: " + (list.indexOf(find(list, "Chi")) < list.indexOf(find(list, "Binh")));
    }

    private static Student find(List<Student> list, String name) {
        for (Student student : list) {
            if (student.name().equals(name)) {
                return student;
            }
        }
        throw new IllegalArgumentException(name);
    }

    public static String comparators() {
        List<Student> byName = students();
        byName.sort(Comparator.comparing(Student::name));
        List<Student> byScoreThenName = students();
        byScoreThenName.sort(Comparator.comparingInt(Student::score).thenComparing(Student::name));
        List<Student> descending = students();
        descending.sort(Comparator.comparingInt(Student::score).reversed().thenComparing(Student::name));
        return "by name " + byName + ", score then name " + byScoreThenName + ", score descending then name " + descending;
    }

    public static String subtractionOverflows() {
        List<Integer> numbers = new ArrayList<>(List.of(Integer.MAX_VALUE, -5, Integer.MIN_VALUE, 0));
        List<Integer> wrong = new ArrayList<>(numbers);
        wrong.sort((a, b) -> a - b);
        List<Integer> right = new ArrayList<>(numbers);
        right.sort(Integer::compare);
        return "a - b gives " + wrong + ", Integer.compare gives " + right;
    }

    public static String consistencyWithEquals() {
        Set<BigDecimal> hash = new HashSet<>();
        Set<BigDecimal> tree = new TreeSet<>();
        for (String text : new String[] {"1.0", "1.00"}) {
            hash.add(new BigDecimal(text));
            tree.add(new BigDecimal(text));
        }
        return "1.0 equals 1.00 is " + new BigDecimal("1.0").equals(new BigDecimal("1.00")) + ", compareTo is " + new BigDecimal("1.0").compareTo(new BigDecimal("1.00"))
                + ", HashSet size " + hash.size() + ", TreeSet size " + tree.size();
    }

    public static String treeSetUsesTheComparator() {
        Set<String> sensitive = new TreeSet<>(List.of("b", "B", "a"));
        Set<String> insensitive = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        insensitive.addAll(List.of("b", "B", "a"));
        return "natural order " + sensitive + ", ignoring case " + insensitive + " (B is a duplicate of b)";
    }

    public static String nullsInSorting() {
        List<String> names = new ArrayList<>(java.util.Arrays.asList("pear", null, "apple"));
        try {
            Collections.sort(names);
            return "sorted";
        } catch (NullPointerException e) {
            names.sort(Comparator.nullsFirst(Comparator.naturalOrder()));
            return "natural order throws NullPointerException; nullsFirst gives " + names;
        }
    }

    public static String notComparable() {
        Set<Object> set = new TreeSet<>();
        try {
            set.add(new Object());
            return "added";
        } catch (ClassCastException e) {
            return "ClassCastException: an Object is not Comparable";
        }
    }
}
