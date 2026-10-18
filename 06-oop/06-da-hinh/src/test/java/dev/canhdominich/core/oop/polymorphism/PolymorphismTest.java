package dev.canhdominich.core.oop.polymorphism;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class PolymorphismTest {

    @Test
    void eachDiscountAppliesItsOwnRuleThroughTheSameCall() {
        assertThat(PolymorphismDemo.strategies()).isEqualTo("price 100000 -> 85000 after 10 percent, 5000 off, none; price 3000 -> 0");
        assertThat(Checkout.total(1_000, List.of())).isEqualTo(1_000);
    }

    @Test
    void anEqualsThatTakesTheSpecificTypeOverloadsInsteadOfOverriding() {
        assertThat(PolymorphismDemo.brokenEquals()).isEqualTo(
                "a.equals(b) true, a.equals((Object) b) false, list.contains(b) false, set.contains(b) false");
    }

    @Test
    void anEqualsThatTakesObjectOverridesAndCollectionsFindTheElement() {
        assertThat(PolymorphismDemo.goodEquals()).isEqualTo(
                "a.equals(b) true, a.equals((Object) b) true, list.contains(b) true, set.contains(b) true, toString (1, 2)");
    }

    @Test
    void staticMethodsAreHiddenAndPrivateMethodsAreNeverReplaced() {
        assertThat(PolymorphismDemo.staticAndPrivate()).isEqualTo(
                "static via Base Base.who and via Derived Derived.who, reveal() on a Derived object Base.secret, Derived.secret() called directly Derived.secret, kind() Derived");
    }

    @Test
    void anOverridingMethodMayReturnAMoreSpecificType() {
        assertThat(PolymorphismDemo.covariantReturn()).isEqualTo(
                "create() through Base gives a Derived, and on Derived the compiler knows it is Derived: Derived");
    }

    @Test
    void theOverrideAnnotationTurnsASilentTypoIntoACompileError() {
        assertThat(Javac.messages("B", "class A { void run() { } } class B extends A { @Override void rn() { } }"))
                .containsExactly("line 1: method does not override or implement a method from a supertype");
        assertThat(Javac.messages("B", "class A { void f(int x) { } } class B extends A { @Override void f(long x) { } }"))
                .containsExactly("line 1: method does not override or implement a method from a supertype");
    }

    @Test
    void overridingRulesRejectDifferentReturnTypesStaticMethodsAndBroaderExceptions() {
        assertThat(Javac.messages("B", "class A { int f() { return 1; } } class B extends A { String f() { return \"\"; } }"))
                .containsExactly("line 1: f() in B cannot override f() in A");
        assertThat(Javac.messages("B", "class A { void f() { } } class B extends A { static void f() { } }"))
                .containsExactly("line 1: f() in B cannot override f() in A");
        assertThat(Javac.messages("B", "class A { void f() { } } class B extends A { void f() throws Exception { } }"))
                .containsExactly("line 1: f() in B cannot override f() in A");
    }
}
