package dev.canhdominich.core.recursion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RecursionTest {

    @BeforeEach
    void resetState() {
        Recursion.calls = 0;
        Counter.reset();
    }

    @Test
    void factorialNeedsABaseCase() {
        assertThat(Recursion.factorial(0)).isEqualTo(1);
        assertThat(Recursion.factorial(5)).isEqualTo(120);
        assertThat(Recursion.factorial(20)).isEqualTo(2432902008176640000L);
        assertThatThrownBy(() -> Recursion.factorial(-1)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void factorialSilentlyWrapsAroundAfter20ButTheExactVersionThrows() {
        assertThat(Recursion.factorial(21)).isNegative();
        assertThatThrownBy(() -> Recursion.factorialExact(21)).isInstanceOf(ArithmeticException.class).hasMessage("long overflow");
    }

    @Test
    void naiveFibonacciRepeatsWorkAndMemoizationRemovesIt() {
        assertThat(Recursion.fibNaive(30)).isEqualTo(832040);
        assertThat(Recursion.calls).isEqualTo(2_692_537);
        Recursion.calls = 0;
        assertThat(Recursion.fibMemo(30, new HashMap<>())).isEqualTo(832040);
        assertThat(Recursion.calls).isEqualTo(59);
        assertThat(Recursion.fibIterative(30)).isEqualTo(832040);
    }

    @Test
    void halvingTheExponentNeedsLogarithmicallyManyCalls() {
        assertThat(Recursion.powerLinear(2, 30)).isEqualTo(1_073_741_824L);
        assertThat(Recursion.calls).isEqualTo(31);
        Recursion.calls = 0;
        assertThat(Recursion.powerFast(2, 30)).isEqualTo(1_073_741_824L);
        assertThat(Recursion.calls).isEqualTo(6);
    }

    @Test
    void recursionOnDigitsAndDisks() {
        assertThat(Recursion.sumDigits(9875)).isEqualTo(29);
        assertThat(Recursion.hanoiMoves(3)).isEqualTo(7);
        assertThat(Recursion.hanoiMoves(10)).isEqualTo(1023);
    }

    @Test
    void aMissingBaseCaseEndsInAStackOverflowError() {
        assertThatThrownBy(() -> Recursion.neverStops(1)).isInstanceOf(StackOverflowError.class);
    }

    @Test
    void aBiggerStackAllowsADeeperRecursion() throws InterruptedException {
        int small = Recursion.measureDepth(256 * 1024);
        int large = Recursion.measureDepth(16L * 1024 * 1024);
        assertThat(small).isGreaterThan(100);
        assertThat(large).isGreaterThan(small);
    }

    @Test
    void staticStateIsSharedAndInstanceStateIsNot() {
        Counter a = new Counter();
        Counter b = new Counter();
        a.increment();
        a.increment();
        b.increment();
        assertThat(a.value()).isEqualTo(2);
        assertThat(b.value()).isEqualTo(1);
        assertThat(Counter.created()).isEqualTo(2);
        assertThat(a.describe()).isEqualTo("value 2 of 2 counters");
    }

    @Test
    void aStaticFactoryCanTouchPrivateInstanceState() {
        Counter c = Counter.withStart(10);
        assertThat(c.value()).isEqualTo(10);
        assertThat(Counter.created()).isEqualTo(1);
    }

    @Test
    void theCompilerKeepsStaticCodeAwayFromInstanceMembers() {
        assertThat(Javac.messages("A", "class A { int value; static int read() { return value; } }"))
                .containsExactly("line 1: non-static variable value cannot be referenced from a static context");
        assertThat(Javac.messages("A", "class A { static A self() { return this; } }"))
                .containsExactly("line 1: non-static variable this cannot be referenced from a static context");
        assertThat(Javac.messages("A", "class A { void work() { } static void run() { work(); } }"))
                .containsExactly("line 1: non-static method work() cannot be referenced from a static context");
        assertThat(Javac.messages("A", "class A { static int total = 3; int read() { return total; } }")).isEmpty();
    }
}
