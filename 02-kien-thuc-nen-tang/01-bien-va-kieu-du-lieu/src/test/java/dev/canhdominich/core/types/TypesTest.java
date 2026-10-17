package dev.canhdominich.core.types;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class TypesTest {

    @Test
    void sizesFollowTheLanguageSpecification() {
        assertThat(new int[] {Byte.SIZE, Short.SIZE, Integer.SIZE, Long.SIZE, Float.SIZE, Double.SIZE, Character.SIZE}).containsExactly(8, 16, 32, 64, 32, 64, 16);
    }

    @Test
    void fieldsGetDefaultValuesButNotLocals() {
        assertThat(new Defaults().describe()).contains("int=0", "boolean=false", "char=\\u0000", "String=null", "int[]=null");
        assertThat(Javac.messages("A", "class A { void f() { int age; System.out.println(age); } }")).containsExactly("line 1: variable age might not have been initialized");
    }

    @Test
    void integersWrapAroundSilently() {
        assertThat(Integer.MAX_VALUE + 1).isEqualTo(Integer.MIN_VALUE);
        assertThat((byte) 200).isEqualTo((byte) -56);
        assertThat(1_000_000 * 1_000_000).isEqualTo(-727379968);
        assertThat(1_000_000L * 1_000_000).isEqualTo(1_000_000_000_000L);
        assertThat(Overflow.addExactMessage()).isEqualTo("ArithmeticException: integer overflow");
    }

    @Test
    void castingToIntTruncatesTowardZeroAndSaturates() {
        assertThat((int) 3.99).isEqualTo(3);
        assertThat((int) -3.99).isEqualTo(-3);
        assertThat((int) 1e20).isEqualTo(Integer.MAX_VALUE);
    }

    @Test
    void binaryFloatingPointCannotStoreOneTenthExactly() {
        assertThat(0.1 + 0.2).isNotEqualTo(0.3);
        assertThat(new BigDecimal("0.1").add(new BigDecimal("0.2"))).isEqualByComparingTo("0.3");
        assertThat(Double.NaN == Double.NaN).isFalse();
    }

    @Test
    void aCharIsOneUtf16UnitNotAlwaysOneCharacter() {
        assertThat("😀".length()).isEqualTo(2);
        assertThat("😀".codePointCount(0, 2)).isEqualTo(1);
        assertThat('A' + 1).isEqualTo(66);
    }

    @Test
    void referencesShareOneObject() {
        ReferencesDemo.Box a = new ReferencesDemo.Box(1);
        ReferencesDemo.Box b = a;
        b.value = 99;
        assertThat(a.value).isEqualTo(99);
        assertThat(new String("java") == new String("java")).isFalse();
        assertThat("java" == "java").isTrue();
    }

    @Test
    void theNullPointerMessageNamesTheVariable() {
        assertThat(ReferencesDemo.nullMessage()).isEqualTo("Cannot invoke \"String.length()\" because \"name\" is null");
    }

    @Test
    void staticFieldsBelongToTheClassAndInstanceFieldsToTheObject() {
        int before = ScopeAndVar.User.count;
        new ScopeAndVar.User("An");
        new ScopeAndVar.User("Binh");
        assertThat(ScopeAndVar.User.count).isEqualTo(before + 2);
    }

    @Test
    void varNeedsAnInitializerAndKeepsItsType() {
        assertThat(Javac.messages("A", "class A { void f() { var age; } }").get(0)).contains("cannot infer type for local variable age");
        assertThat(Javac.messages("A", "class A { var name = \"An\"; }").get(0)).contains("'var' is not allowed here");
        assertThat(Javac.messages("A", "class A { void f() { var number = 10; number = \"Java\"; } }").get(0)).contains("incompatible types");
    }
}
