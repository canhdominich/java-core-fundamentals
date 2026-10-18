package dev.canhdominich.core.loops;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class LoopsTest {

    @Test
    void forRunsOnlyWhileTheConditionHolds() {
        assertThat(LoopForms.forLoop(1, 5)).containsExactly(1, 2, 3, 4, 5);
        assertThat(LoopForms.forLoop(5, 1)).isEmpty();
    }

    @Test
    void whileMayRunZeroTimesButDoWhileRunsAtLeastOnce() {
        assertThat(LoopForms.whileLoop(10)).isZero();
        assertThat(LoopForms.doWhileLoop(10)).isEqualTo(1);
        assertThat(LoopForms.whileLoop(1)).isEqualTo(5);
        assertThat(LoopForms.doWhileLoop(1)).isEqualTo(5);
    }

    @Test
    void aForHeaderCanDeclareTwoVariablesAndStepByAnyAmount() {
        assertThat(LoopForms.twoVariables()).isEqualTo("(0,5)(1,4)(2,3)");
        assertThat(LoopForms.stepsOfThree()).isEqualTo("10 7 4 1");
    }

    @Test
    void forEachCannotChangeTheArrayElementsThroughItsVariable() {
        int[] numbers = {10, 20, 30};
        assertThat(LoopForms.sumWithForEach(numbers)).isEqualTo(60);
        assertThat(LoopForms.doubleWithForEach(numbers)).containsExactly(10, 20, 30);
        assertThat(LoopForms.doubleWithIndex(numbers)).containsExactly(20, 40, 60);
    }

    @Test
    void lessThanOrEqualToTheLengthReadsOnePastTheEnd() {
        assertThat(LoopPitfalls.offByOne(new int[] {5, 6, 7}))
                .isEqualTo("total so far 18, then ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3");
    }

    @Test
    void aSemicolonAfterTheForHeaderMakesTheBodyAnEmptyStatement() {
        assertThat(LoopPitfalls.strayCount()).isEqualTo(1);
    }

    @Test
    void floatingPointAndNarrowCountersNeverReachTheirLimit() {
        assertThat(LoopPitfalls.floatCounter(14)).hasSize(14);
        assertThat(LoopPitfalls.floatCounter(14)).doesNotContain(1.0);
        assertThat(LoopPitfalls.byteCounter(1000)).isEqualTo(1000);
        assertThat(LoopPitfalls.shrinkingBound()).isEqualTo(3);
    }

    @Test
    void breakLeavesTheLoopAndContinueSkipsOneRound() {
        assertThat(BreakContinue.breakAt(5)).containsExactly(1, 2, 3, 4);
        assertThat(BreakContinue.oddOnly(9)).containsExactly(1, 3, 5, 7, 9);
    }

    @Test
    void aLabelLetsBreakLeaveTheOuterLoop() {
        int[][] grid = {{4, 9, 4}, {7, 4, 1}, {4, 2, 8}};
        assertThat(BreakContinue.firstPairWithoutLabel(grid, 4)).isEqualTo("(2,0)");
        assertThat(BreakContinue.firstPairWithLabel(grid, 4)).isEqualTo("(0,0)");
        assertThat(BreakContinue.iterationsOf(3, 3, false)).isEqualTo(8);
        assertThat(BreakContinue.iterationsOf(3, 3, true)).isEqualTo(5);
    }

    @Test
    void aLabeledContinueSkipsToTheNextCandidate() {
        assertThat(BreakContinue.primesBelow(30)).containsExactly(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
    }

    @Test
    void fizzBuzzFollowsTheDivisibilityRules() {
        List<String> out = Practice.fizzBuzz(15);
        assertThat(out).hasSize(15);
        assertThat(out.get(2)).isEqualTo("Fizz");
        assertThat(out.get(4)).isEqualTo("Buzz");
        assertThat(out.get(14)).isEqualTo("FizzBuzz");
    }

    @Test
    void digitLoopsWorkOnNegativeNumbers() {
        assertThat(Practice.digitSum(9875)).isEqualTo(29);
        assertThat(Practice.digitSum(-406)).isEqualTo(10);
        assertThat(Practice.reverseDigits(12340)).isEqualTo(4321);
        assertThat(Practice.reverseDigits(-57)).isEqualTo(-75);
    }

    @Test
    void collatzCountsStepsAndRejectsNonPositiveStarts() {
        assertThat(Practice.collatzSteps(6)).isEqualTo(8);
        assertThat(Practice.collatzSteps(27)).isEqualTo(111);
        assertThatThrownBy(() -> Practice.collatzSteps(0)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void binarySearchNeedsFarFewerStepsThanLinearSearch() {
        int[] sorted = new int[1000];
        for (int i = 0; i < sorted.length; i++) {
            sorted[i] = i * 2;
        }
        assertThat(Practice.linearSearchSteps(sorted, 1998)).isEqualTo(1000);
        assertThat(Practice.binarySearchSteps(sorted, 1998)).isEqualTo(10);
    }
}
