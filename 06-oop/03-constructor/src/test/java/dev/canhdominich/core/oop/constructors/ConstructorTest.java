package dev.canhdominich.core.oop.constructors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class ConstructorTest {

    @Test
    void assigningAParameterToItselfLeavesTheFieldAtItsDefault() {
        assertThat(ConstructorDemo.shadowing()).isEqualTo("buggy: name null, age 0 | fixed: name An, age 30");
    }

    @Test
    void aConstructorCanRefuseToCreateAnInvalidObject() {
        assertThatThrownBy(() -> new Person("", 20)).isInstanceOf(IllegalArgumentException.class).hasMessage("name must not be blank");
        assertThatThrownBy(() -> new Person("An", -1)).hasMessage("age out of range: -1");
        assertThatThrownBy(() -> new Person((String) null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void overloadedConstructorsFillInDefaultsAndCopyConstructorsCopyFields() {
        assertThat(ConstructorDemo.validation()).isEqualTo(
                "name must not be blank; age out of range: -1; age out of range: 200; defaults: Binh 18, copy: Binh 18");
    }

    @Test
    void chainedConstructorsRunTheMostCompleteBodyFirst() {
        assertThat(ConstructorDemo.chaining()).containsExactly(
                "Order(id, quantity, price) body", "Order(id, quantity) body", "Order(id) body", "A1 x1 at 9.99");
    }

    @Test
    void theSuperclassIsInitialisedBeforeTheSubclass() {
        assertThat(ConstructorDemo.initialisationOrder()).containsExactly(
                "Parent field initializer", "Parent initializer block", "Parent constructor", "Child.describe sees label=null",
                "Child field initializer", "Child initializer block", "Child constructor sees label=ready");
    }

    @Test
    void anOverriddenMethodCalledFromTheParentConstructorSeesDefaultFields() {
        assertThat(ConstructorDemo.parentSeesDefaults()).isEqualTo("seen by the parent constructor: label=null, label afterwards: ready");
    }

    @Test
    void aPrivateConstructorForcesCreationThroughAFactoryThatCanReuseInstances() {
        assertThat(ConstructorDemo.privateConstructor()).isEqualTo("of(0) == ZERO true, of(5) == of(5) false, of(5).amount 5");
        assertThatThrownBy(() -> Money.of(-1)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void storingACallerArrayWithoutCopyingLetsTheCallerChangeTheObject() {
        assertThat(ConstructorDemo.defensiveCopy()).isEqualTo("after changing the caller array: sharing 99, copying 1");
    }

    @Test
    void theCompilerChecksConstructorRules() {
        assertThat(Javac.messages("A", "class P { P(int x) { } } class A { P p = new P(); }"))
                .containsExactly("line 1: constructor P in class P cannot be applied to given types;");
        assertThat(Javac.messages("P", "class P { P() { int a = 1; this(2); } P(int x) { } }"))
                .containsExactly("line 1: call to this must be first statement in constructor");
        assertThat(Javac.messages("P", "class P { P() { this(1); } P(int x) { this(); } }"))
                .containsExactly("line 1: recursive constructor invocation");
        assertThat(Javac.messages("P", "class P { final int x; P() { } }"))
                .containsExactly("line 1: variable x might not have been initialized");
        assertThat(Javac.messages("A", "class P { void P() { } } class A { P p = new P(); }")).isEmpty();
        assertThat(Javac.messages("A", "class A { Object f() { return new dev.canhdominich.core.oop.constructors.Person(null); } }"))
                .containsExactly("line 1: reference to Person is ambiguous");
    }
}
