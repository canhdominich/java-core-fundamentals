package dev.canhdominich.core.collections.setmap;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SetMapTest {

    @Test
    void setAlgebraUsesAddAllRetainAllAndRemoveAll() {
        assertThat(Sets.algebra()).isEqualTo("a [1, 2, 3, 4], b [3, 4, 5], union [1, 2, 3, 4, 5], intersection [3, 4], a minus b [1, 2]");
        assertThat(Sets.addReturnsWhetherItChanged()).isEqualTo("first add true, second add of the same false, size 1");
    }

    @Test
    void overridingEqualsWithoutHashCodeBreaksHashBasedLookups() {
        assertThat(Sets.contractWithoutHashCode()).isEqualTo(
                "equals but no hashCode: contains false, size after adding an equal point 2 | equals and hashCode: contains true");
        assertThat(SetMapDemo.lostKey()).isEqualTo("equals only: get null | equals and hashCode: get home");
    }

    @Test
    void treeSetAnswersNearestElementQuestions() {
        assertThat(Sets.treeSetOperations()).isEqualTo(
                "first 10, last 40, ceiling(25) 30, floor(25) 20, headSet(30) [10, 20], tailSet(30) [30, 40]");
    }

    @Test
    void putReturnsTheOldValueAndGetOrDefaultAvoidsNull() {
        assertThat(Maps.basics()).isEqualTo(
                "first put returns null, second returns the old value 5, putIfAbsent left apple at 8, get(missing) null, getOrDefault 0, containsKey true");
    }

    @Test
    void mergeCountsAndComputeIfAbsentGroups() {
        assertThat(Maps.wordCount()).isEqualTo("{be=2, not=1, or=1, to=2}");
        assertThat(Maps.groupBy()).isEqualTo("{3=[fig], 4=[pear, kiwi, plum, date], 5=[apple]}");
    }

    @Test
    void mapsIterateOverEntriesAndExposeWriteThroughViews() {
        assertThat(Maps.iteration()).isEqualTo("coffee=5 milk=2 tea=3 | keys [coffee, milk, tea] | values [5, 2, 3]");
        assertThat(Maps.viewsWriteThrough()).isEqualTo("after removing through keySet and values the map is {}");
        assertThat(Maps.immutableMap()).isEqualTo("Map.of is immutable: UnsupportedOperationException");
    }

    @Test
    void aTreeMapFindsTheNearestKey() {
        assertThat(Maps.treeMapNavigation()).isEqualTo("floorEntry(79) 65=B, ceilingKey(51) 65, firstKey 0, headMap(65) {0=F, 50=C}");
    }

    @Test
    void anAccessOrderedLinkedHashMapMakesAnLruCache() {
        assertThat(SetMapDemo.lruCache()).isEqualTo("capacity 3, after touching a and adding d the cache holds [c, a, d] and b was evicted: true");
    }

    @Test
    void identicalHashCodesTurnALookupIntoAScan() {
        assertThat(SetMapDemo.badHashCost(1000)).isEqualTo("1000 keys with the same hashCode: one lookup made more than 50 equals calls: true (the exact number varies from run to run)");
    }
}
