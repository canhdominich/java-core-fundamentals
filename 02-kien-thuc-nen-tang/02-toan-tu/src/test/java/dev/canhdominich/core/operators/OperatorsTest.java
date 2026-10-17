package dev.canhdominich.core.operators;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class OperatorsTest {

    @Test
    void integerDivisionDropsTheFractionAndModuloKeepsTheDividendSign() {
        assertThat(7 / 2).isEqualTo(3);
        assertThat(-7 / 2).isEqualTo(-3);
        assertThat(-7 % 3).isEqualTo(-1);
        assertThat(Math.floorMod(-7, 3)).isEqualTo(2);
        assertThat((double) 7 / 2).isEqualTo(3.5);
        assertThat((double) (7 / 2)).isEqualTo(3.0);
    }

    @Test
    void dividingAnIntegerByZeroThrowsButADoubleGivesInfinity() {
        assertThat(Arithmetic.divideByZero()).isEqualTo("ArithmeticException: / by zero");
        assertThat(5.0 / 0).isEqualTo(Double.POSITIVE_INFINITY);
    }

    @Test
    void plusIsLeftToRightAndConcatenatesAfterAString() {
        assertThat("1" + 2 + 3).isEqualTo("123");
        assertThat(1 + 2 + "3").isEqualTo("33");
        assertThat('a' + 1).isEqualTo(98);
    }

    @Test
    void compoundAssignmentCastsButPlainAssignmentDoesNot() {
        byte b = 10;
        b += 300;
        assertThat(b).isEqualTo((byte) 54);
        assertThat(Javac.messages("A", "class A { void f() { byte b = 10; b = b + 5; } }")).containsExactly("line 1: incompatible types: possible lossy conversion from int to byte");
        int y = 10;
        y *= 2.5;
        assertThat(y).isEqualTo(25);
    }

    @Test
    void prefixAndPostfixDifferInTheValueOfTheExpression() {
        int a = 5;
        int b = a++;
        int c = ++a;
        assertThat(new int[] {a, b, c}).containsExactly(7, 5, 7);
        assertThat(IncrementTraps.selfAssignment()).isEqualTo(5);
    }

    @Test
    void shortCircuitSkipsTheRightSideButSingleOperatorsDoNot() {
        assertThat(Logic.callsFor(() -> false && Logic.tick(true))).isZero();
        assertThat(Logic.callsFor(() -> false & Logic.tick(true))).isEqualTo(1);
        assertThat(Logic.callsFor(() -> true || Logic.tick(false))).isZero();
        assertThat(Logic.callsFor(() -> true | Logic.tick(false))).isEqualTo(1);
        assertThat(Logic.safeLength(null)).isFalse();
        org.junit.jupiter.api.Assertions.assertThrows(NullPointerException.class, () -> Logic.unsafeLength(null));
    }

    @Test
    void boxedIntegersAreSharedOnlyForSmallValues() {
        Integer a = 127;
        Integer b = 127;
        Integer c = 128;
        Integer d = 128;
        assertThat(a == b).isTrue();
        assertThat(c == d).isFalse();
        assertThat(c.equals(d)).isTrue();
    }

    @Test
    void bitFlagsCanBeCombinedTestedAndCleared() {
        int permissions = Bits.READ | Bits.WRITE;
        assertThat(Bits.has(permissions, Bits.WRITE)).isTrue();
        assertThat(Bits.has(permissions, Bits.EXECUTE)).isFalse();
        assertThat(Bits.remove(permissions, Bits.WRITE)).isEqualTo(Bits.READ);
        assertThat(permissions ^ Bits.EXECUTE).isEqualTo(7);
    }

    @Test
    void shiftsKeepOrDropTheSignAndWrapTheDistance() {
        assertThat(-8 >> 1).isEqualTo(-4);
        assertThat(-8 >>> 1).isEqualTo(2147483644);
        assertThat(1 << 32).isEqualTo(1);
        assertThat(1 << 33).isEqualTo(2);
        assertThat(~5).isEqualTo(-6);
    }

    @Test
    void multiplicationBindsTighterThanAdditionAndAndTighterThanOr() {
        assertThat(2 + 3 * 4).isEqualTo(14);
        assertThat(true || false && false).isTrue();
        assertThat(Precedence.grade(85) + Precedence.grade(70) + Precedence.grade(55) + Precedence.grade(10)).isEqualTo("ABCF");
    }
}
