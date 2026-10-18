package dev.canhdominich.core.arraytools;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ArrayToolsTest {

    @Test
    void sortCanWorkOnTheWholeArrayOrOnARange() {
        assertThat(ArraysTour.sorting()).isEqualTo("sorted [1, 3, 5, 7, 9], only indexes 1 to 3 [9, 1, 3, 7, 5]");
    }

    @Test
    void binarySearchReturnsTheIndexOrMinusInsertionPointMinusOne() {
        assertThat(ArraysTour.binarySearch()).isEqualTo("find 30 -> 2, find 5 -> -1, find 35 -> -4, find 99 -> -6");
        assertThat(ArraysTour.insertionPoint(new int[] {10, 20, 30, 40, 50}, 35)).isEqualTo(3);
        assertThat(ArraysTour.insertionPoint(new int[] {10, 20, 30, 40, 50}, 30)).isEqualTo(2);
    }

    @Test
    void binarySearchOnAnUnsortedArrayCanMissAValueThatIsPresent() {
        assertThat(ArraysTour.binarySearchOnUnsorted()).isEqualTo("find 30 in an unsorted array -> -1");
    }

    @Test
    void fillCanCoverTheWholeArrayOrARange() {
        assertThat(ArraysTour.fillAndCopy()).isEqualTo("fill [7, 7, 7, 7, 7], fill indexes 1 to 2 [0, 9, 9, 0, 0]");
    }

    @Test
    void equalsOfArraysIsIdentityAndDeepEqualsLooksInsideNestedArrays() {
        assertThat(ArraysTour.comparing()).isEqualTo("a == b false, equals false, Arrays.equals true, nested Arrays.equals false, deepEquals true, same hashCode true");
    }

    @Test
    void mismatchFindsTheFirstDifferingIndex() {
        assertThat(ArraysTour.mismatchAndCompare()).isEqualTo("mismatch at 2, mismatch of equal arrays -1, compare -1, compare reversed 1");
    }

    @Test
    void toStringHandlesNullEmptyAndNestedArrays() {
        assertThat(ArraysTour.printing()).isEqualTo("null array -> null, empty -> [], nested -> [[1], [2, 3], []]");
    }

    @Test
    void asListIsAFixedSizeViewThatWritesThroughToTheArray() {
        assertThat(AsListTraps.writeThrough()).isEqualTo("list [Anh, Binh, Cuong], array [Anh, Binh, Cuong]");
        assertThat(AsListTraps.fixedSize()).isEqualTo("UnsupportedOperationException, message null");
    }

    @Test
    void asListOnAPrimitiveArrayGivesAListWithASingleElement() {
        assertThat(AsListTraps.primitiveArray()).isEqualTo("from int[] the list size is 1, from Integer[] the list size is 3");
    }

    @Test
    void listOfCopiesTheArrayWhileAsListDoesNot() {
        assertThat(AsListTraps.immutableCopy()).isEqualTo("List.of copy [An, Binh], array [Changed, Binh]");
    }

    @Test
    void sortingObjectsIsStableAndAComparatorChangesTheOrder() {
        assertThat(SortingChoices.stableByLength()).isEqualTo("[c, e, bb, aa, dd, ff]");
        assertThat(SortingChoices.caseInsensitive()).isEqualTo("natural [Apple, Cherry, apple, banana], ignoring case [apple, Apple, banana, Cherry]");
        assertThat(SortingChoices.descending()).isEqualTo("[9, 7, 3, 1]");
        assertThat(SortingChoices.reverseAnIntArray()).isEqualTo("[9, 7, 3, 1]");
    }

    @Test
    void aPrimitiveArrayCannotBeSortedWithAComparator() {
        assertThat(Javac.messages("A", "import java.util.*; class A { void f(int[] a) { Arrays.sort(a, Comparator.reverseOrder()); } }"))
                .hasSize(1)
                .allMatch(message -> message.startsWith("line 1: no suitable method found for sort(int[],"));
    }
}
