package dev.canhdominich.core.java8.stream;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StreamTest {

    @Test
    void aPipelineFiltersMapsDeduplicatesSortsAndCollects() {
        assertThat(Pipelines.basicPipeline()).isEqualTo("customers with an order of at least 100000: [Binh, Chi], revenue 800000");
    }

    @Test
    void intermediateOperationsAreLazyAndFindFirstStopsEarly() {
        assertThat(Pipelines.laziness()).isEqualTo("building the pipeline touched 0 elements, findFirst returned 2 after seeing 2 elements");
    }

    @Test
    void aStreamCanBeConsumedOnlyOnce() {
        assertThat(Pipelines.reuseFails()).isEqualTo("a stream cannot be reused: stream has already been operated upon or closed");
    }

    @Test
    void groupingByCountsAndSumsAndPartitioningSplitsInTwo() {
        assertThat(Pipelines.groupingAndPartitioning()).isEqualTo(
                "orders per customer {An=2, Binh=2, Chi=2}, revenue per customer {An=130000, Binh=410000, Chi=260000}, big [Binh/book, Binh/bag, Chi/book], small [An/pen, An/book, Chi/pen]");
    }

    @Test
    void toMapRejectsDuplicateKeysUnlessYouGiveAMergeFunction() {
        assertThat(Pipelines.toMapAndDuplicates()).isEqualTo(
                "toMap with duplicate keys throws IllegalStateException (Duplicate key...), with a merge function it gives {An=130000, Binh=410000, Chi=260000}");
    }

    @Test
    void primitiveStreamsSumAverageAndSummarise() {
        assertThat(Pipelines.primitiveStreams()).isEqualTo("sum 1 to 100 is 5050, min 1, max 9, average 2.5, boxed [0, 1, 2, 3, 4]");
    }

    @Test
    void flatMapFlattensAndJoiningBuildsText() {
        assertThat(Pipelines.joiningAndFlatMap()).isEqualTo("flattened [a, b, c], joined [x, y, z], words from sentences [to, be, or, not]");
    }

    @Test
    void streamToListIsImmutableWhileCollectorsToListIsNotGuaranteedToBeButIsMutableHere() {
        assertThat(Pipelines.listsFromStreams()).isEqualTo("Collectors.toList can be extended [1, 2, 3], Stream.toList add: UnsupportedOperationException");
        assertThat(Pipelines.sourceIsNotModified()).isEqualTo("source [3, 1, 2], sorted copy [1, 2, 3]");
    }

    @Test
    void theCompilerRejectsAccumulatingIntoALocalVariable() {
        assertThat(Javac.messages("A", "import java.util.*; class A { void f(List<Integer> l) { int total = 0; l.stream().forEach(n -> total += n); } }"))
                .containsExactly("line 1: local variables referenced from a lambda expression must be final or effectively final");
    }
}
