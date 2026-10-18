package dev.canhdominich.core.collections.ordering;

public class OrderingDemo {

    public static void main(String[] args) {
        System.out.println("iterable          = " + Ordering.customIterable());
        System.out.println("natural order     = " + Ordering.naturalOrder());
        System.out.println("comparators       = " + Ordering.comparators());
        System.out.println("subtraction bug   = " + Ordering.subtractionOverflows());
        System.out.println("equals consistency= " + Ordering.consistencyWithEquals());
        System.out.println("tree set          = " + Ordering.treeSetUsesTheComparator());
        System.out.println("nulls             = " + Ordering.nullsInSorting());
        System.out.println("not comparable    = " + Ordering.notComparable());
        String noCompare = "import java.util.*; class P { } class A { void f(List<P> l) { Collections.sort(l); } }";
        String wrongSignature = "class P implements Comparable<P> { public int compareTo(Object o) { return 0; } }";
        String missingMethod = "class P implements Comparable<P> { }";
        String comparatorLambdaType = "import java.util.*; class A { void f(List<String> l) { l.sort((a, b) -> a.length() > b.length()); } }";
        System.out.println("sort a list of a class that is not Comparable = " + Javac.messages("A", noCompare));
        System.out.println("compareTo with the wrong parameter type       = " + Javac.messages("P", wrongSignature));
        System.out.println("Comparable without compareTo                  = " + Javac.messages("P", missingMethod));
        System.out.println("comparator returning boolean                  = " + Javac.messages("A", comparatorLambdaType));
    }
}
