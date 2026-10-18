package dev.canhdominich.core.overloading;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class OverloadingTest {

    @Test
    void theNumberAndTypesOfArgumentsSelectTheMethod() {
        assertThat(Calculator.add(1, 2)).isEqualTo(3);
        assertThat(Calculator.add(1, 2, 3)).isEqualTo(6);
        assertThat(Calculator.add(1.5, 2.5)).isEqualTo(4.0);
        assertThat(Calculator.add("1", "2")).isEqualTo("12");
    }

    @Test
    void anIntAndADoubleWidenToTheDoubleOverload() {
        assertThat(Calculator.add(1, 2.5)).isEqualTo(3.5);
    }

    @Test
    void twoCharsWidenToIntAndAreAddedAsNumbers() {
        assertThat(Calculator.add('a', 'b')).isEqualTo(195);
    }

    @Test
    void theOrderOfParameterTypesIsPartOfTheSignature() {
        assertThat(Calculator.mixed(1, 2.0)).isEqualTo("int, double");
        assertThat(Calculator.mixed(1.0, 2)).isEqualTo("double, int");
    }

    @Test
    void wideningBeatsBoxingAndBoxingBeatsVarargs() {
        assertThat(Resolution.widening(5)).isEqualTo("long");
        assertThat(Resolution.widening('a')).isEqualTo("long");
        assertThat(Resolution.widening(5.0)).isEqualTo("Object");
        assertThat(Resolution.widening(Integer.valueOf(5))).isEqualTo("Integer");
        assertThat(Resolution.boxing(5)).isEqualTo("Integer");
        assertThat(Resolution.boxing(5L)).isEqualTo("Object");
        assertThat(Resolution.onlyVarargs(5)).isEqualTo("Object");
    }

    @Test
    void varargsIsTheLastResort() {
        assertThat(Resolution.widening()).isEqualTo("int...");
        assertThat(Resolution.widening(1, 2)).isEqualTo("int...");
        assertThat(Resolution.boxing(5, 6)).isEqualTo("int...");
        assertThat(Resolution.onlyVarargs()).isEqualTo("int... with 0");
    }

    @Test
    void theCompileTimeTypeChoosesTheOverload() {
        Object runtimeString = "text";
        assertThat(StaticDispatch.show(runtimeString)).isEqualTo("Object");
        assertThat(StaticDispatch.show((String) runtimeString)).isEqualTo("String");
        assertThat(StaticDispatch.show(null)).isEqualTo("String");
    }

    @Test
    void listRemoveWithAnIntRemovesByIndexAndWithAnIntegerRemovesByValue() {
        List<Integer> list = List.of(10, 20, 30, 1);
        assertThat(StaticDispatch.removeByIndex(list, 1)).containsExactly(10, 30, 1);
        assertThat(StaticDispatch.removeByValue(list, 1)).containsExactly(10, 20, 30);
    }

    @Test
    void theCompilerRejectsAmbiguousAndDuplicateDeclarations() {
        assertThat(Javac.messages("A", "class A { String pick(String s) { return \"s\"; } String pick(StringBuilder b) { return \"b\"; } String g() { return pick(null); } }"))
                .containsExactly("line 1: reference to pick is ambiguous");
        assertThat(Javac.messages("A", "class A { int f(int x) { return x; } long f(int x) { return x; } }"))
                .containsExactly("line 1: method f(int) is already defined in class A");
        assertThat(Javac.messages("A", "class A { int f(int x) { return x; } int f(double x) { return 0; } int g() { return f(\"a\"); } }"))
                .containsExactly("line 1: no suitable method found for f(java.lang.String)");
    }
}
