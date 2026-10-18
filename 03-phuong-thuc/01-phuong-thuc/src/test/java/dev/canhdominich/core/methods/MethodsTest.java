package dev.canhdominich.core.methods;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class MethodsTest {

    @Test
    void twoCopiesOfTheSameRuleCanDisagree() {
        assertThat(Duplication.cartTotalCopy(5)).isEqualTo(5);
        assertThat(Duplication.invoiceTotalCopy(5)).isEqualTo(6);
    }

    @Test
    void oneSharedMethodGivesEveryCallerTheSameAnswer() {
        for (long net : new long[] {5, 15, 100, 995}) {
            assertThat(Duplication.cartTotal(net)).isEqualTo(Duplication.invoiceTotal(net));
        }
    }

    @Test
    void returnTypesCarryNumbersRecordsAndFlags() {
        int[] data = {4, 9, 1, 7};
        assertThat(MethodShapes.average(data)).isEqualTo(5.25);
        assertThat(MethodShapes.rangeOf(data)).isEqualTo(new MethodShapes.Range(1, 9));
        assertThat(MethodShapes.isLeapYear(1900)).isFalse();
        assertThat(MethodShapes.isLeapYear(2000)).isTrue();
    }

    @Test
    void averageOfAnEmptyArrayIsNaNNotAnException() {
        assertThat(MethodShapes.average(new int[0])).isNaN();
    }

    @Test
    void guardClausesGiveTheSameResultsAsNestedIfs() {
        for (int age : new int[] {-1, 0, 30, 129, 130, 200}) {
            for (boolean member : new boolean[] {true, false}) {
                assertThat(MethodShapes.describeGuarded(age, member)).isEqualTo(MethodShapes.describeNested(age, member));
            }
        }
    }

    @Test
    void argumentsAreEvaluatedLeftToRightBeforeTheCall() {
        assertThat(MethodShapes.evaluationOrder()).containsExactly("first", "second", "third", "sum3");
    }

    @Test
    void theCallStackListsCallersFromTheInnermostOutward() {
        List<String> frames = CallStack.outer();
        assertThat(frames.subList(0, 3)).containsExactly("inner", "middle", "outer");
    }

    @Test
    void compilerMessagesNameTheProblem() {
        assertThat(Javac.messages("A", "class A { int f(int x) { if (x > 0) { return 1; } } }"))
                .containsExactly("line 1: missing return statement");
        assertThat(Javac.messages("A", "class A { int f() { return 1; System.out.println(2); } }"))
                .containsExactly("line 1: unreachable statement");
        assertThat(Javac.messages("A", "class A { void log() { } void g() { int x = log(); } }"))
                .containsExactly("line 1: incompatible types: void cannot be converted to int");
    }
}
