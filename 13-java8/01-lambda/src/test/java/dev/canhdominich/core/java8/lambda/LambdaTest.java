package dev.canhdominich.core.java8.lambda;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class LambdaTest {

    @Test
    void lambdasImplementTheSingleMethodOfAFunctionalInterface() {
        assertThat(Syntax.forms()).isEqualTo("square(7) 49, add(2, 3) 5, hello hello, shout HEY!, empty(\"\") true, block(5) 11");
    }

    @Test
    void fourKindsOfMethodReferenceReplaceLambdas() {
        assertThat(Syntax.methodReferences()).isEqualTo(
                "static 42, bound instance Dr. Who, unbound instance 4, constructor [], unbound with an argument true");
    }

    @Test
    void andThenAndComposeApplyFunctionsInOppositeOrders() {
        assertThat(Syntax.composition()).isEqualTo(
                "andThen(plusOne, timesTwo) of 5 is 12, compose is 11, positive and even for 4 true, positive and even for -4 false, negate of even for 3 true");
    }

    @Test
    void lambdasCaptureValuesAndCanChangeTheContentsOfACapturedArray() {
        assertThat(Syntax.capturingVariables()).isEqualTo("captured a final value: 15, changed a captured array element twice: 2");
    }

    @Test
    void aSupplierDelaysTheExpensiveWorkButAnArgumentDoesNot() {
        assertThat(Syntax.laziness()).isEqualTo("eager argument ran the expensive code 1 time, lazy supplier added 0 more");
    }

    @Test
    void sortingAcceptsALambdaAsAComparator() {
        assertThat(Syntax.sortWithLambdas()).isEqualTo("by length [fig, kiwi, apple, banana], natural [apple, banana, fig, kiwi]");
    }

    @Test
    void thisInsideALambdaIsTheEnclosingObjectButInAnAnonymousClassItIsTheClassItself() {
        ThisMeaning meaning = new ThisMeaning();
        assertThat(meaning.insideLambda()).isEqualTo("this is the outer object");
        assertThat(meaning.insideAnonymousClass()).isEqualTo("this is the anonymous object");
    }

    @Test
    void theCompilerEnforcesTheRulesOfLambdas() {
        assertThat(Javac.messages("A", "import java.util.function.*; class A { void f() { int n = 1; Runnable r = () -> n++; } }"))
                .containsExactly("line 1: local variables referenced from a lambda expression must be final or effectively final");
        assertThat(Javac.messages("A", "class A { void f() { Object o = () -> 1; } }"))
                .containsExactly("line 1: incompatible types: java.lang.Object is not a functional interface");
        assertThat(Javac.messages("A", "import java.util.function.*; class A { void f() { Function<Integer, Integer> g = (a, b) -> a + b; } }"))
                .containsExactly("line 1: incompatible types: incompatible parameter types in lambda expression");
    }
}
