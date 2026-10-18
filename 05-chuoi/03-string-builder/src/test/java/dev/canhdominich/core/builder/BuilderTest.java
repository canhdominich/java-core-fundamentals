package dev.canhdominich.core.builder;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class BuilderTest {

    @Test
    void appendAcceptsAnyTypeAndReturnsTheBuilderForChaining() {
        assertThat(BuilderOps.chaining()).isEqualTo("Java 17 true 1.5 null");
    }

    @Test
    void insertReplaceDeleteAndSetCharAtEditInPlace() {
        assertThat(BuilderOps.editing()).isEqualTo(">> JELLO!");
    }

    @Test
    void reverseSupportsAPalindromeCheck() {
        assertThat(BuilderOps.reversing("Java")).isEqualTo("avaJ");
        assertThat(BuilderOps.isPalindrome("A man, a plan, a canal: Panama")).isTrue();
        assertThat(BuilderOps.isPalindrome("Java")).isFalse();
    }

    @Test
    void trimmingTheTrailingSeparatorWithSetLengthHandlesAnEmptyList() {
        assertThat(BuilderOps.joinWithSeparator(List.of("An", "Binh", "Chi"))).isEqualTo("An, Binh, Chi");
        assertThat(BuilderOps.joinWithSeparator(List.of())).isEmpty();
    }

    @Test
    void equalsOnABuilderIsIdentityButCompareToLooksAtTheText() {
        assertThat(BuilderOps.equalityTraps()).isEqualTo("equals false, same text true, compareTo 0, contentEquals true");
    }

    @Test
    void outOfRangeEditsThrowButAnOverlongDeleteEndIsClamped() {
        assertThat(BuilderOps.deleteAndInsertErrors())
                .isEqualTo("insert(5): offset 5, length 3 | deleteCharAt(3): index 3, length 3 | delete(1, 99) is allowed: a");
    }

    @Test
    void setLengthPadsWithNulCharactersOrTruncates() {
        assertThat(BuilderOps.setLengthPadsWithNulChars()).isEqualTo("length 5 with 2 NUL characters, truncated ab");
    }

    @Test
    void capacityStartsSmallAndRoughlyDoublesWhenFull() {
        assertThat(Capacity.initial()).isEqualTo("default 16, from text of 5 21, requested 100 100");
        assertThat(Capacity.growth(1000)).containsExactly(16, 34, 70, 142, 286, 574, 1150);
        assertThat(Capacity.trimAndEnsure()).isEqualTo("before 16, after trimToSize 5, after ensureCapacity(500) 500, length stays 5");
    }

    @Test
    void bothWaysOfAppendingBuildTheSameText() {
        assertThat(AppendSpeed.withPlusEquals(2000)).isEqualTo(AppendSpeed.withBuilder(2000)).isEqualTo(2000);
    }

    @Test
    void aSharedStringBufferNeverLosesAppendsWhileAStringBuilderUsuallyDoes() throws InterruptedException {
        int perThread = 100_000;
        int[] buffer = ThreadSafety.race(new StringBuffer(), perThread);
        assertThat(buffer[0]).isEqualTo(2 * perThread);
        assertThat(buffer[1]).isZero();
        int[] builder = ThreadSafety.race(new StringBuilder(), perThread);
        assertThat(builder[0]).isLessThanOrEqualTo(2 * perThread);
    }
}
