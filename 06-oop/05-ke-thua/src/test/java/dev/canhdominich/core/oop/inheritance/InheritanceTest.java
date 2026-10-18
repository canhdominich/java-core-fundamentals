package dev.canhdominich.core.oop.inheritance;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class InheritanceTest {

    @Test
    void aSubclassUsesInheritedMethodsAndProtectedFields() {
        assertThat(InheritanceDemo.inheritedMembers()).isEqualTo("name Rex (inherited), energy 12 after eat and fetch, Rex fetches");
    }

    @Test
    void overridingChangesBehaviourAndSuperReusesTheParentVersion() {
        assertThat(InheritanceDemo.overriding()).isEqualTo("Generic says ... | Rex says woof and wags its tail | Tom says meow |");
    }

    @Test
    void fieldsAreChosenByTheDeclaredTypeWhileMethodsAreChosenByTheRealObject() {
        assertThat(InheritanceDemo.fieldsAreNotPolymorphic()).isEqualTo(
                "viaAnimal.label animal, viaDog.label dog, ((Dog) viaAnimal).label dog, but sound() is woof either way");
    }

    @Test
    void castingToTheWrongSubclassFailsAtRunTime() {
        assertThat(InheritanceDemo.casting()).isEqualTo(
                "instanceof Cat true, instanceof Dog false, cast to Dog throws ClassCastException, message mentions the two classes true, pattern match gives meow");
    }

    @Test
    void everyClassHasObjectAtTheTopOfItsChain() {
        assertThat(InheritanceDemo.everythingIsAnObject()).isEqualTo(
                "superclass of Dog Animal, of Animal Object, toString starts with the class name true, equals is identity false");
    }

    @Test
    void overridingAddAllInAHashSetCountsEveryElementTwice() {
        assertThat(InheritanceDemo.fragileBaseClass()).isEqualTo(
                "inheritance counts 6 attempts for 3 elements, composition counts 3; both hold 3 and 3 elements");
    }

    @Test
    void theCompilerEnforcesTheRulesOfInheritance() {
        assertThat(Javac.messages("B", "final class A { } class B extends A { }")).containsExactly("line 1: cannot inherit from final A");
        assertThat(Javac.messages("B", "class A { final void f() { } } class B extends A { void f() { } }"))
                .containsExactly("line 1: f() in B cannot override f() in A");
        assertThat(Javac.messages("B", "class A { A(int x) { } } class B extends A { B() { int y = 1; super(y); } }"))
                .containsExactly("line 1: call to super must be first statement in constructor");
        assertThat(Javac.messages("B", "class A { private int secret; } class B extends A { int f() { return secret; } }"))
                .containsExactly("line 1: secret has private access in A");
        assertThat(Javac.messages("B", "class A { A(String name) { } } class B extends A { }"))
                .containsExactly("line 1: constructor A in class A cannot be applied to given types;");
    }
}
