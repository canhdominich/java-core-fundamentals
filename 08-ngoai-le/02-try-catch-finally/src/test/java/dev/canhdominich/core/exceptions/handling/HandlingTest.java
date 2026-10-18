package dev.canhdominich.core.exceptions.handling;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class HandlingTest {

    @Test
    void finallyRunsInEveryCase() {
        assertThat(Flow.run("5")).containsExactly("try starts", "parsed 5", "result 20", "finally", "after the try statement");
        assertThat(Flow.run("abc")).containsExactly("try starts", "catch NumberFormatException", "finally", "after the try statement");
        assertThat(Flow.run("0")).containsExactly("try starts", "parsed 0", "catch ArithmeticException", "finally", "after the try statement");
    }

    @Test
    void finallyAlsoRunsWhileAnExceptionTravelsToAnOuterCatch() {
        assertThat(Flow.uncaught()).containsExactly("inner try", "inner finally", "outer catch boom");
    }

    @Test
    void theReturnValueIsFixedBeforeFinallyRuns() {
        Trace.clear();
        assertThat(Flow.returnInFinally()).isEqualTo("from try");
        assertThat(Trace.steps()).containsExactly("finally ran before the return completed");
        assertThat(Flow.returnValueIsCaptured()).isEqualTo(1);
    }

    @Test
    void aReturnInsideFinallyHidesTheException() {
        assertThat(Flow.finallyOverrides()).isEqualTo("finally returned, the exception vanished");
    }

    @Test
    void swallowingMakesDifferentSituationsLookTheSame() {
        assertThat(Flow.parseOrZero("abc")).isEqualTo(Flow.parseOrZero("0")).isZero();
        assertThatThrownBy(() -> Flow.parseOrReport("abc"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("not a number: 'abc'")
                .hasCauseInstanceOf(NumberFormatException.class);
    }

    @Test
    void oneCatchCanHandleSeveralUnrelatedTypes() {
        assertThat(Flow.multiCatch("x")).isEqualTo("multi-catch caught NumberFormatException");
        assertThat(Flow.multiCatch(null)).isEqualTo("multi-catch caught NullPointerException");
        assertThat(Flow.multiCatch("7")).isEqualTo("7");
    }

    @Test
    void resourcesCloseInReverseOrderAndEvenWhenTheBodyFails() {
        assertThat(Resources.orderOfClosing()).containsExactly("open A", "open B", "use A", "use B", "close B", "close A");
        assertThat(Resources.closedEvenWhenTheBodyFails()).containsExactly("open A", "use A", "close A", "caught use failed on A");
    }

    @Test
    void aFailureWhileClosingIsAttachedAsSuppressedInsteadOfReplacingTheOriginal() {
        assertThat(Resources.suppressed()).isEqualTo(
                "caught IllegalStateException: use failed on A | suppressed IllegalArgumentException: close failed on A");
    }

    @Test
    void handWrittenFinallyBlocksEitherSwallowOrReplaceTheOriginal() {
        Trace.clear();
        assertThat(Resources.manualFinallySwallowingTheCloseFailure()).isEqualTo("caught use failed on A");
        assertThat(Trace.steps()).contains("close failure swallowed");
        assertThat(Resources.manualFinallyThrowing()).isEqualTo("caught IllegalArgumentException: close failed on A, suppressed 0");
    }

    @Test
    void theCompilerChecksTheShapeOfTryStatements() {
        assertThat(Javac.messages("A", "class A { void f() { try { int x = 1; } } }"))
                .containsExactly("line 1: 'try' without 'catch', 'finally' or resource declarations");
        assertThat(Javac.messages("A", "class A { void f() { try { int x = 1; } catch (RuntimeException | IllegalStateException e) { } } }"))
                .containsExactly("line 1: Alternatives in a multi-catch statement cannot be related by subclassing");
        assertThat(Javac.messages("A", "class A { void f() { try (String s = \"x\") { } } }"))
                .containsExactly("line 1: incompatible types: try-with-resources not applicable to variable type");
    }
}
