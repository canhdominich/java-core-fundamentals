package dev.canhdominich.core.parameters;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ParametersTest {

    @Test
    void aPrimitiveParameterIsACopy() {
        int n = 5;
        PassByValue.changeInt(n);
        assertThat(n).isEqualTo(5);
    }

    @Test
    void anObjectParameterCopiesTheReferenceSoFieldChangesAreVisible() {
        Box box = new Box(5);
        PassByValue.changeField(box);
        assertThat(box.value).isEqualTo(99);
    }

    @Test
    void reassigningTheParameterDoesNotChangeTheCallersVariable() {
        Box box = new Box(5);
        PassByValue.reassign(box);
        assertThat(box.value).isEqualTo(5);
    }

    @Test
    void swappingTheParametersFailsButSwappingContentsWorks() {
        Box a = new Box(1);
        Box b = new Box(2);
        PassByValue.swap(a, b);
        assertThat(a.value).isEqualTo(1);
        assertThat(b.value).isEqualTo(2);
        PassByValue.swapContents(a, b);
        assertThat(a.value).isEqualTo(2);
        assertThat(b.value).isEqualTo(1);
    }

    @Test
    void theParameterAndTheOriginalAreTheSameObject() {
        Box a = new Box(1);
        assertThat(PassByValue.isSameObject(a, a)).isTrue();
        assertThat(PassByValue.isSameObject(new Box(1), a)).isFalse();
    }

    @Test
    void stringsAreImmutableButBuildersAreNot() {
        String text = "hi";
        assertThat(PassByValue.appendText(text)).isEqualTo("hi!");
        assertThat(text).isEqualTo("hi");
        StringBuilder builder = new StringBuilder("hi");
        PassByValue.appendBuilder(builder);
        assertThat(builder).hasToString("hi!");
    }

    @Test
    void sortingInPlaceChangesTheCallersArrayButACopyDoesNot() {
        int[] data = {3, 1, 2};
        assertThat(PassByValue.sortedCopy(data)).containsExactly(1, 2, 3);
        assertThat(data).containsExactly(3, 1, 2);
        PassByValue.sortInPlace(data);
        assertThat(data).containsExactly(1, 2, 3);
    }

    @Test
    void varargsAcceptsZeroValuesManyValuesOrAnArray() {
        assertThat(VarargsDemo.sum()).isZero();
        assertThat(VarargsDemo.sum(1, 2, 3)).isEqualTo(6);
        assertThat(VarargsDemo.sum(new int[] {4, 5})).isEqualTo(9);
        assertThat(VarargsDemo.describe("none")).isEqualTo("none: 0 values []");
    }

    @Test
    void aVarargsParameterIsTheCallersArrayWhenAnArrayIsPassed() {
        int[] original = {1, 2, 3};
        VarargsDemo.zeroOut(original);
        assertThat(original).containsExactly(0, 0, 0);
    }

    @Test
    void theFixedArityOverloadWinsOverVarargs() {
        assertThat(VarargsDemo.chooseOverload(1, 2)).isEqualTo("two ints");
        assertThat(VarargsDemo.chooseOverload(1, 2, 3)).isEqualTo("varargs of 3");
        assertThat(VarargsDemo.chooseOverload()).isEqualTo("varargs of 0");
    }

    @Test
    void passingANullArrayToVarargsThrows() {
        assertThat(VarargsDemo.nullArray()).startsWith("NullPointerException: Cannot read the array length");
    }

    @Test
    void varargsMustBeTheLastParameter() {
        assertThat(Javac.messages("A", "class A { void f(int... a, String b) { } }"))
                .containsExactly("line 1: varargs parameter must be the last parameter");
        assertThat(Javac.messages("A", "class A { void f(final int x) { x = 5; } }"))
                .containsExactly("line 1: final parameter x may not be assigned");
    }
}
