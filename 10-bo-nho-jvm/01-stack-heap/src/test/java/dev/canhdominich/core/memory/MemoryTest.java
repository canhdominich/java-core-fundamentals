package dev.canhdominich.core.memory;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class MemoryTest {

    @Test
    void objectSizesIncludeAHeaderAndAreRoundedToEightBytes() {
        assertThat(SizeProbe.sizes()).containsExactly(
                "new Object()           = 16 bytes",
                "new Pair (two ints)    = 24 bytes",
                "new Wide (three longs) = 40 bytes",
                "new int[0]             = 16 bytes",
                "new int[10]            = 56 bytes",
                "new byte[100]          = 120 bytes",
                "new String(\"abc\")      = 24 bytes (the characters are shared with the literal)",
                "Long.valueOf(100000 + i) = 24 bytes");
    }

    @Test
    void aPrimitiveParameterIsACopyOnTheStackWhileAnArrayLivesOnTheHeap() {
        assertThat(MemoryDemo.stackAndHeapByBehaviour()).isEqualTo("int stays 5, array element becomes 99");
    }

    @Test
    void eachThreadHasItsOwnLocalsButSharesTheHeap() throws InterruptedException {
        int[] result = ThreadStacks.run(500_000);
        assertThat(result[0]).isEqualTo(500_000);
        assertThat(result[1]).isEqualTo(500_000);
        assertThat(result[2]).isLessThanOrEqualTo(1_000_000);
    }

    @Test
    void aSmallHeapEndsInAnOutOfMemoryErrorWithAHeapSpaceMessage() {
        assertThat(MemoryDemo.limitedHeap()).isEqualTo(
                "OutOfMemoryError message: Java heap space\nallocated megabytes before failing is below the 64 limit: true and above 20: true");
    }

    @Test
    void theRuntimeReportsItsHeapLimits() {
        assertThat(MemoryDemo.heapNumbers()).isEqualTo("max heap is larger than the committed heap or equal: true, processors true");
    }
}
