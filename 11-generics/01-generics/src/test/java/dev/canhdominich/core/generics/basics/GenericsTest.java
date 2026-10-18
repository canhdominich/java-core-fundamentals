package dev.canhdominich.core.generics.basics;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class GenericsTest {

    @Test
    void anObjectBoxMovesTheTypeErrorToRunTime() {
        assertThat(GenericsDemo.beforeGenerics()).isEqualTo("the mistake shows up only at run time: ClassCastException");
    }

    @Test
    void aGenericBoxNeedsNoCast() {
        assertThat(GenericsDemo.withGenerics()).isEqualTo("no cast needed: text, length 4");
    }

    @Test
    void genericMethodsWorkWithAnyTypeAndInferTheTypeArguments() {
        assertThat(GenericsDemo.genericMethods()).isEqualTo(
                "first pear and 7, max pear and 42, listOf [1, 2, 3], pair Pair[first=a, second=1], swapped Pair[first=1, second=a]");
        assertThatThrownBy(() -> Util.first(List.<String>of())).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void mappingAGenericBoxChangesItsTypeArgument() {
        assertThat(GenericsDemo.mappingABox()).isEqualTo("Box<String> to Box<Integer> to Box<String>: 5 then length=5");
    }

    @Test
    void aRawTypeCanPutTheWrongTypeIntoAGenericListAndTheFailureAppearsLater() {
        assertThat(GenericsDemo.heapPollution()).isEqualTo(
                "the Integer slipped in through a raw type and failed at the read, naming both classes: true");
    }

    @Test
    void boxedIntegersAreSharedOnlyForSmallValues() {
        assertThat(GenericsDemo.boxingIdentity()).isEqualTo("127 == 127 is true, 128 == 128 is false, equals gives true");
        assertThat(GenericsDemo.noPrimitives()).startsWith("primitives are boxed as Integer: stored [5], read back 5");
    }

    @Test
    void theCompilerRejectsWrongElementTypesAndIllegalGenericConstructs() {
        assertThat(Javac.messages("A", "import java.util.*; class A { void f() { List<String> l = new ArrayList<>(); l.add(5); } }"))
                .containsExactly("line 1: incompatible types: int cannot be converted to java.lang.String");
        assertThat(Javac.messages("A", "import java.util.*; class A { List<int> l; }")).containsExactly("line 1: unexpected type");
        assertThat(Javac.messages("A", "class A<T> { T[] make() { return new T[3]; } }")).containsExactly("line 1: generic array creation");
        assertThat(Javac.messages("A", "class A<T> { static T shared; }"))
                .containsExactly("line 1: non-static type variable T cannot be referenced from a static context");
        assertThat(Javac.messages("A", "class Box2<T extends Comparable<T>> { } class A { Box2<Object> b; }"))
                .containsExactly("line 1: type argument java.lang.Object is not within bounds of type-variable T");
    }
}
