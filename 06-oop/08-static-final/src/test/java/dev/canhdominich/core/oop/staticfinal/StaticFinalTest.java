package dev.canhdominich.core.oop.staticfinal;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StaticFinalTest {

    @Test
    void aCompileTimeConstantDoesNotInitialiseTheClassButAMethodCallDoes() {
        assertThat(StaticFinalDemo.lazyInitialisation("method")).isEqualTo(
                "[read the compile-time constant 42, field initializer runs, static block runs, helper runs, second use, constructor runs]");
    }

    @Test
    void aFieldComputedAtRunTimeAlsoInitialisesTheClassOnFirstRead() {
        assertThat(StaticFinalDemo.lazyInitialisation("computed")).isEqualTo(
                "[read the compile-time constant 42, field initializer runs, static block runs, read the computed value 1002, second use, constructor runs]");
    }

    @Test
    void aFinalReferenceStillAllowsTheObjectItPointsToToChange() {
        assertThat(StaticFinalDemo.constantsAreNotDeepFinal()).startsWith("hosts [a.example, b.example, c.example], ports 8080 and 443");
        assertThat(Config.HOSTS).contains("c.example");
        assertThat(Config.MAX_RETRIES).isEqualTo(3);
    }

    @Test
    void finalFieldsAreSetOnceInTheConstructorAndAStaticCounterNumbersObjects() {
        assertThat(StaticFinalDemo.finalFieldsAndNumbering()).isEqualTo("numbers 1000, 1001, next customer Binh, amount 700");
    }

    @Test
    void finalLocalsKeepTheirReferenceButNotNecessarilyTheirContents() {
        assertThat(StaticFinalDemo.finalLocalAndReference()).isEqualTo("start then changed, fixed 10");
    }

    @Test
    void theCompilerEnforcesSingleAssignment() {
        assertThat(Javac.messages("A", "class A { void f() { final int x = 1; x = 2; } }")).containsExactly("line 1: cannot assign a value to final variable x");
        assertThat(Javac.messages("A", "class A { void f(final int x) { x = 2; } }")).containsExactly("line 1: final parameter x may not be assigned");
        assertThat(Javac.messages("A", "class A { final int x; }")).containsExactly("line 1: variable x not initialized in the default constructor");
        assertThat(Javac.messages("B", "class A { final void f() { } } class B extends A { void f() { } }"))
                .containsExactly("line 1: f() in B cannot override f() in A");
    }
}
