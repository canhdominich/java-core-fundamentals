package dev.canhdominich.core.collections.list;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ListTest {

    @Test
    void addInsertSetAndSearchWork() {
        assertThat(ListOps.basics()).isEqualTo(
                "after add, insert and set [Anh, Binh, Chi], set returned the old value An, indexOf(Chi) 2, indexOf(Zed) -1, contains(Binh) true, size 3");
    }

    @Test
    void removeWithAnIntRemovesByIndexAndWithAnIntegerRemovesByValue() {
        assertThat(ListOps.removeByIndexOrByValue()).isEqualTo(
                "remove(1) removes the element at index 1: [10, 30, 1], remove(Integer.valueOf(1)) removes the value 1: [10, 30]");
    }

    @Test
    void anIndexOutsideTheListThrows() {
        assertThat(ListOps.outOfRange()).isEqualTo("IndexOutOfBoundsException: Index 2 out of bounds for length 2");
    }

    @Test
    void equalityLooksAtTheElementsInOrderNotAtTheClass() {
        assertThat(ListOps.listEqualsComparesElementsInOrder()).isEqualTo("ArrayList equals LinkedList true, equals List.of true, different order false");
    }

    @Test
    void aSubListSharesDataWithItsParent() {
        assertThat(ListOps.subListIsAView()).isEqualTo(
                "writing through the view changed the parent: [1, 99, 3, 4, 5], structural change of the parent then using the view: ConcurrentModificationException");
        assertThat(ListOps.listIteratorWorksBackwards()).isEqualTo("backwards cba");
        assertThat(ListOps.fixedSizeAndImmutableLists()).isEqualTo("Arrays.asList set works [z, b], add: UnsupportedOperationException");
    }

    @Test
    void removingInsideForEachBreaksTheIterator() {
        assertThat(Removal.removeInsideForEach()).isEqualTo("ConcurrentModificationException with message null, the list is now [1, 3, 4, 5, 6]");
        assertThat(Removal.removeTheSecondToLastInsideForEach()).startsWith("no exception, the list is [1, 3]");
    }

    @Test
    void aForwardIndexLoopSkipsElementsAfterEachRemoval() {
        assertThat(Removal.removeByIndexLoopSkipsElements()).startsWith("forward index loop left [4, 7]");
    }

    @Test
    void fourSafeWaysToRemoveGiveTheSameResult() {
        assertThat(Removal.removeWithIterator()).isEqualTo("Iterator.remove gives [1, 3, 5]");
        assertThat(Removal.removeIf()).isEqualTo("removeIf gives [1, 3, 5] and reports changed true");
        assertThat(Removal.removeBackwards()).isEqualTo("backward index loop gives [1, 3, 5]");
        assertThat(Removal.copyThenRemove()).isEqualTo("iterating over a copy gives [1, 3, 5]");
    }
}
