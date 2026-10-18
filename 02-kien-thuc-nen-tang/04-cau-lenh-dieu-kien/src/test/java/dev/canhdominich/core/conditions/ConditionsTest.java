package dev.canhdominich.core.conditions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class ConditionsTest {

    @Test
    void conditionsAreCheckedTopToBottomSoOrderMatters() {
        assertThat(IfPitfalls.grade(95)).isEqualTo("A");
        assertThat(IfPitfalls.grade(49)).isEqualTo("F");
        assertThat(IfPitfalls.wrongOrder(95)).isEqualTo("C");
    }

    @Test
    void anElseBelongsToTheNearestIf() {
        assertThat(IfPitfalls.danglingElse(true, false)).isEqualTo("belongs to the inner if");
        assertThat(IfPitfalls.danglingElse(false, false)).isEqualTo("none");
    }

    @Test
    void assigningInsideTheConditionCompilesAndAlwaysEnters() {
        assertThat(IfPitfalls.assignmentInCondition(false)).isEqualTo("entered, flag is now true");
    }

    @Test
    void equalsSignsOnStringsCompareIdentity() {
        assertThat(IfPitfalls.stringsWithEqualsSigns(new String("yes"))).isFalse();
        assertThat(new String("yes").equals("yes")).isTrue();
    }

    @Test
    void withoutBreakExecutionFallsThroughToTheNextCase() {
        assertThat(OldSwitch.withoutBreak(1)).containsExactly("Monday", "Tuesday", "Wednesday");
        assertThat(OldSwitch.withoutBreak(2)).containsExactly("Tuesday", "Wednesday");
        assertThat(OldSwitch.withoutBreak(3)).containsExactly("Wednesday");
        assertThat(OldSwitch.withoutBreak(9)).containsExactly("another day");
    }

    @Test
    void casesCanBeGroupedAndStringsAreMatchedExactly() {
        assertThat(OldSwitch.groupedCases(12)).isEqualTo("winter");
        assertThat(OldSwitch.onText("Stop")).isEqualTo("unknown command Stop");
        assertThat(OldSwitch.nullSelector()).startsWith("NullPointerException");
    }

    @Test
    void aSwitchExpressionReturnsAValueAndNeedsEveryCase() {
        assertThat(SwitchExpressions.kind(Day.SUNDAY)).isEqualTo("weekend");
        assertThat(SwitchExpressions.length(Day.WEDNESDAY)).isEqualTo(9);
        assertThat(SwitchExpressions.statusText(503)).isEqualTo("server error");
        assertThat(Javac.messages("A", "class A { int f(int x) { return switch (x) { case 1 -> 10; case 2 -> 20; }; } }").get(0)).contains("does not cover all possible input values");
    }

    @Test
    void theCompilerRejectsMixedKindsAndDuplicateLabels() {
        assertThat(Javac.messages("A", "class A { int f(int x) { return switch (x) { case 1 -> 10; case 2: yield 20; default -> 0; }; } }").get(0)).contains("different case kinds");
        assertThat(Javac.messages("A", "class A { int f(int x) { switch (x) { case 1: return 1; case 1: return 2; } return 0; } }").get(0)).contains("duplicate case label");
    }

    @Test
    void shippingCombinesSwitchAndIf() {
        assertThat(Shipping.fee("HN", 1.5, false)).isEqualTo(20_000);
        assertThat(Shipping.fee("HN", 4.2, false)).isEqualTo(35_000);
        assertThat(Shipping.fee("CT", 6.0, true)).isEqualTo(45_000);
        assertThat(Shipping.fee("CT", 6.0, false)).isEqualTo(55_000);
        assertThatThrownBy(() -> Shipping.fee("HN", 0, false)).isInstanceOf(IllegalArgumentException.class);
    }
}
