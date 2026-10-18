package dev.canhdominich.core.collections.ordering;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class OrderingTest {

    @Test
    void anIterableCanBeUsedInForEachAndItsIteratorFailsWhenExhausted() {
        assertThat(Ordering.customIterable()).isEqualTo(
                "for-each over Range(1, 5) sums to 10, a third next() on Range(0, 2): NoSuchElementException: range exhausted at 2");
    }

    @Test
    void compareToDefinesTheNaturalOrderAndSortingIsStable() {
        assertThat(Ordering.naturalOrder()).isEqualTo(
                "sorted by compareTo (score) [Dung:70, Chi:80, Binh:80, An:90], stable for equal scores Chi before Binh: true");
    }

    @Test
    void comparatorsBuildAnyOrderFromSmallKeys() {
        assertThat(Ordering.comparators()).isEqualTo(
                "by name [An:90, Binh:80, Chi:80, Dung:70], score then name [Dung:70, Binh:80, Chi:80, An:90], score descending then name [An:90, Binh:80, Chi:80, Dung:70]");
    }

    @Test
    void subtractionAsAComparatorOverflowsForExtremeValues() {
        assertThat(Ordering.subtractionOverflows()).isEqualTo(
                "a - b gives [0, 2147483647, -2147483648, -5], Integer.compare gives [-2147483648, -5, 0, 2147483647]");
    }

    @Test
    void aTreeSetTreatsCompareToZeroAsADuplicateEvenWhenEqualsDisagrees() {
        assertThat(Ordering.consistencyWithEquals()).isEqualTo(
                "1.0 equals 1.00 is false, compareTo is 0, HashSet size 2, TreeSet size 1");
        assertThat(Ordering.treeSetUsesTheComparator()).isEqualTo("natural order [B, a, b], ignoring case [a, b] (B is a duplicate of b)");
    }

    @Test
    void nullsNeedAnExplicitPolicyAndNonComparableElementsFail() {
        assertThat(Ordering.nullsInSorting()).isEqualTo("natural order throws NullPointerException; nullsFirst gives [null, apple, pear]");
        assertThat(Ordering.notComparable()).isEqualTo("ClassCastException: an Object is not Comparable");
    }

    @Test
    void theCompilerChecksTheComparableContract() {
        assertThat(Javac.messages("A", "import java.util.*; class P { } class A { void f(List<P> l) { Collections.sort(l); } }"))
                .containsExactly("line 1: no suitable method found for sort(java.util.List<P>)");
        assertThat(Javac.messages("P", "class P implements Comparable<P> { }"))
                .containsExactly("line 1: P is not abstract and does not override abstract method compareTo(P) in java.lang.Comparable");
    }
}
