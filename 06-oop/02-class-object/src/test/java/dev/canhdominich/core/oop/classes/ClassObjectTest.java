package dev.canhdominich.core.oop.classes;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClassObjectTest {

    @BeforeEach
    void resetCounter() {
        Student.resetCount();
    }

    @Test
    void fieldsOfANewObjectHoldTheDefaultValueOfTheirType() {
        assertThat(ClassObjectDemo.defaults()).isEqualTo("name null, score 0, passed false, average 0.0, marks null");
    }

    @Test
    void everyObjectHasItsOwnCopyOfTheInstanceFields() {
        assertThat(ClassObjectDemo.independentObjects()).isEqualTo("An has 55 (passed) | Binh has 40 (not passed)");
    }

    @Test
    void assigningAReferenceDoesNotCopyTheObject() {
        assertThat(ClassObjectDemo.twoNamesForOneObject()).isEqualTo("a An has 60 (passed), a == b true, a == c false, a.equals(c) false");
    }

    @Test
    void theDefaultToStringPrintsTheClassNameAndAHash() {
        assertThat(ClassObjectDemo.defaultToString()).isEqualTo("starts with Student@ true");
    }

    @Test
    void callingAMethodThroughANullReferenceNamesTheVariable() {
        assertThat(ClassObjectDemo.nullReference()).isEqualTo(
                "NullPointerException: Cannot invoke \"dev.canhdominich.core.oop.classes.Student.describe()\" because \"missing\" is null");
    }

    @Test
    void anArrayOfObjectsStartsFullOfNulls() {
        assertThat(ClassObjectDemo.arrayOfObjects()).isEqualTo("before [null, null, null], after S3 has 20 (not passed)");
    }

    @Test
    void aStaticFieldCountsAllObjectsOfTheClass() {
        assertThat(ClassObjectDemo.instanceCounter()).isEqualTo("created 3, kept Binh has 2 (not passed)");
    }

    @Test
    void fieldsAlreadyHaveTheirDefaultOrInitialValueWhenTheConstructorStarts() {
        assertThat(ClassObjectDemo.fieldsBeforeTheConstructorBody()).isEqualTo("inside the constructor, first line: 0 and 7; afterwards plain 5");
    }

    @Test
    void theCompilerRejectsInstanceCallsThroughTheClassAndWrongConstructors() {
        assertThat(Javac.messages("A", "class A { void greet() { } static void f() { A.greet(); } }"))
                .containsExactly("line 1: non-static method greet() cannot be referenced from a static context");
        assertThat(Javac.messages("A", "class Student { Student(String name) { } } class A { Student f() { return new Student(); } }"))
                .containsExactly("line 1: constructor Student in class Student cannot be applied to given types;");
        assertThat(Javac.messages("A", "class Student { } class A { void f() { Student s = new Student; } }"))
                .containsExactly("line 1: '(' or '[' expected");
    }
}
