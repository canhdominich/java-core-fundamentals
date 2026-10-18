package dev.canhdominich.core.gc;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class GcTest {

    @Test
    void anObjectIsCollectedOnlyAfterItsLastStrongReferenceIsGone() {
        assertThat(Reachability.strongAndWeak()).isEqualTo(
                "while a strong reference exists the object survives true, after the last strong reference is gone it survives false");
    }

    @Test
    void softReferencesSurviveACollectionButWeakOnesDoNot() {
        assertThat(Reachability.softVersusWeak()).isEqualTo("soft reference survives true, weak reference survives false");
    }

    @Test
    void aCycleOfUnreachableObjectsIsStillCollected() {
        assertThat(Reachability.cycles()).isEqualTo("two objects pointing at each other: first survives false, second survives false");
    }

    @Test
    void aStaticCollectionKeepsObjectsAliveUntilItIsCleared() {
        assertThat(Reachability.staticCacheLeak()).isEqualTo("held by a static list it survives true, after the list is cleared it survives false");
    }

    @Test
    void releasingLargeArraysLowersTheUsedMemory() {
        assertThat(Reachability.usedMemoryShrinks()).isEqualTo("after dropping 50 MB of arrays, used memory went down by at least 40 MB: true");
    }

    @Test
    void theJvmReportsWhichCollectorItUsesAndRunsYoungCollections() {
        assertThat(GcDemo.collectorChoice()).containsExactly(
                "-XX:+UseSerialGC reports [Using Serial], at least one young collection happened true",
                "-XX:+UseParallelGC reports [Using Parallel], at least one young collection happened true",
                "-XX:+UseG1GC reports [Using G1], at least one young collection happened true");
    }
}
