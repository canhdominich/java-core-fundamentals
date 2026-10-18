package dev.canhdominich.core.generics.wildcards;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class WildcardTest {

    @Test
    void anUpperBoundLetsTheMethodCallTheBoundsMethods() {
        assertThat(Bounds.sum(List.of(1, 2, 3))).isEqualTo(6.0);
        assertThat(Bounds.sum(List.of(1.5, 2.5))).isEqualTo(4.0);
        assertThat(Bounds.largest(List.of(3, 9, 4))).isEqualTo(9);
        assertThat(Bounds.largest(List.of(2.5, 1.5))).isEqualTo(2.5);
    }

    @Test
    void extendsWildcardsProduceAndSuperWildcardsConsume() {
        assertThat(Wildcards.usage()).isEqualTo(
                "sum of Integers 6.0, sum of Doubles 4.0, numbers filled [1, 2, 3], objects filled [1, 2], merged [1, 2, 3, 1.5, 2.5], describe size 5: 1 2 3 1.5 2.5");
    }

    @Test
    void differentParameterisationsShareOneClassAtRunTime() {
        assertThat(Erasure.sameClassAtRunTime()).isEqualTo("ArrayList<String> and ArrayList<Integer> share one class: true, the class name is ArrayList");
    }

    @Test
    void erasureReplacesATypeParameterWithItsBound() throws NoSuchMethodException {
        assertThat(Erasure.erasureOfTypeParameters()).isEqualTo("Holder<T extends Number>: get() returns Number, set takes Number (the bound replaces T)");
    }

    @Test
    void theCompilerAddsABridgeMethodForGenericInterfaces() {
        assertThat(Erasure.bridgeMethods()).isEqualTo("StringBox has 1 compiler-made bridge method: compareTo(Object)");
    }

    @Test
    void aClassTokenRestoresTheTypeNeededToCreateArraysAndInstances() throws ReflectiveOperationException {
        assertThat(Erasure.arrayThroughAClassToken()).isEqualTo("created a String[] of length 3 using Class<T> as a token");
        assertThat(Erasure.instanceThroughAClassToken()).isEqualTo("created an empty StringBuilder with capacity 16");
    }

    @Test
    void theCompilerAppliesTheGetAndPutRulesOfWildcards() {
        assertThat(Javac.messages("A", "import java.util.*; class A { void f(List<? extends Number> l) { l.add(5); } }"))
                .containsExactly("line 1: incompatible types: int cannot be converted to capture#1 of ? extends java.lang.Number");
        assertThat(Javac.messages("A", "import java.util.*; class A { void f(List<? extends Number> l) { l.add(null); } }")).isEmpty();
        assertThat(Javac.messages("A", "import java.util.*; class A { Object f(List<? super Integer> l) { return l.get(0); } }")).isEmpty();
        assertThat(Javac.messages("A", "import java.util.*; class A { void f(List<Integer> i) { List<? extends Number> n = i; } }")).isEmpty();
        assertThat(Javac.messages("A", "import java.util.*; class A { void f(List<String> l) { } void f(List<Integer> l) { } }"))
                .containsExactly("line 1: name clash: f(java.util.List<java.lang.Integer>) and f(java.util.List<java.lang.String>) have the same erasure");
    }
}
