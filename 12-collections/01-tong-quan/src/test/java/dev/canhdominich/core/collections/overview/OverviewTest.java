package dev.canhdominich.core.collections.overview;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class OverviewTest {

    @Test
    void theInterfacesFormACollectionFamilyAndMapStandsApart() {
        assertThat(Overview.interfacesOf()).containsExactly(
                "ArrayList implements List, RandomAccess, Cloneable, Serializable",
                "LinkedList implements List, Deque, Cloneable, Serializable",
                "HashSet implements Set, Cloneable, Serializable",
                "TreeSet implements NavigableSet, Cloneable, Serializable",
                "ArrayDeque implements Deque, Cloneable, Serializable",
                "HashMap implements Map, Cloneable, Serializable",
                "TreeMap implements NavigableMap, Cloneable, Serializable");
        assertThat(Overview.subInterfaces()).isEqualTo(
                "List extends Collection, Set extends Collection, Collection extends Iterable, Map extends nothing: true");
    }

    @Test
    void eachCollectionIteratesInItsOwnOrder() {
        assertThat(Overview.iterationOrders()).isEqualTo(
                "list [pear, apple, fig, banana, cherry], HashSet [banana, apple, cherry, pear, fig], LinkedHashSet [pear, apple, fig, banana, cherry], TreeSet [apple, banana, cherry, fig, pear]");
    }

    @Test
    void listsKeepDuplicatesSetsDoNotAndNullKeysDependOnTheMap() {
        assertThat(Overview.duplicatesAndNulls()).isEqualTo(
                "list keeps duplicates 3, set keeps 2, HashMap null key allowed and size 2 with x=3, TreeMap null key: NullPointerException");
    }

    @Test
    void listOfCreatesAnImmutableListThatRejectsNulls() {
        assertThat(Overview.immutableFactories()).isEqualTo(
                "List.of add: UnsupportedOperationException, List.of with null: NullPointerException, a mutable copy has 4 elements");
    }

    @Test
    void aHashSetFindsAKeyWithOneEqualsCallWhileAListScansEverything() {
        assertThat(Overview.lookupCost(1000)).isEqualTo(
                "searching for the last of 1000 keys: list true after 1000 equals calls, HashSet true after 1 equals call");
        assertThat(Overview.lookupCost(100_000)).isEqualTo(
                "searching for the last of 100000 keys: list true after 100000 equals calls, HashSet true after 1 equals call");
    }

    @Test
    void collectionsUtilityMethodsSortReverseAndWrap() {
        assertThat(Overview.utilityMethods()).isEqualTo(
                "sorted [1, 3, 3, 5, 9], reversed [9, 5, 3, 3, 1], max 9, min 1, frequency of 3 is 2, unmodifiable view add: UnsupportedOperationException");
    }

    @Test
    void theCompilerSeparatesIndexedListsFromSetsAndMaps() {
        assertThat(Javac.messages("A", "import java.util.*; class A { String f(Set<String> s) { return s.get(0); } }"))
                .containsExactly("line 1: cannot find symbol");
        assertThat(Javac.messages("A", "import java.util.*; class A { List<String> l = new List<>(); }"))
                .containsExactly("line 1: java.util.List is abstract; cannot be instantiated");
        assertThat(Javac.messages("A", "import java.util.*; class A { Collection<String> f(Map<String, Integer> m) { return m; } }"))
                .hasSize(1).allMatch(m -> m.startsWith("line 1: incompatible types: java.util.Map"));
    }
}
