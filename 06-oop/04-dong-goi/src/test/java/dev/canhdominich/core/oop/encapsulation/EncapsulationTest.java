package dev.canhdominich.core.oop.encapsulation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class EncapsulationTest {

    @Test
    void publicFieldsAllowAnyoneToBreakTheRules() {
        assertThat(EncapsulationDemo.leakyAccount()).isEqualTo("owner null, balance -500.0 (nothing stopped it)");
    }

    @Test
    void privateFieldsBehindMethodsKeepTheObjectValid() {
        assertThat(EncapsulationDemo.safeAccount()).isEqualTo(
                "deposit must be positive: -5; deposit must be positive: 0; cannot withdraw 500 from 100; balance 150, operations 1");
        assertThatThrownBy(() -> new SafeAccount(" ", 0)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new SafeAccount("An", -1)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void aGetterThatReturnsTheInternalListLetsCallersBypassTheCapacityRule() {
        assertThat(EncapsulationDemo.leakyCourse()).isEqualTo("third enrolment accepted false, capacity 2, students [An, Binh, Chi, Dung]");
    }

    @Test
    void returningAnUnmodifiableCopyProtectsTheInternalList() {
        assertThat(EncapsulationDemo.safeCourse()).isEqualTo("add to the returned list: UnsupportedOperationException, students [An, Binh]");
    }

    @Test
    void theInternalRepresentationCanChangeWithoutAffectingCallers() {
        assertThat(EncapsulationDemo.hiddenRepresentation()).isEqualTo(
                "celsius 36.6, fahrenheit 97.9, freezing 32.0 F, below absolute zero: -26.85 K");
    }

    @Test
    void theFourAccessLevelsBehaveAsDocumentedFromAnotherPackage() {
        String prefix = "package other; ";
        String type = "dev.canhdominich.core.oop.encapsulation.Visibility";
        assertThat(Javac.messages("Probe", prefix + "public class Probe { int f(" + type + " v) { return v.privateLevel; } }"))
                .containsExactly("line 1: privateLevel has private access in " + type);
        assertThat(Javac.messages("Probe", prefix + "public class Probe { int f(" + type + " v) { return v.packageLevel; } }"))
                .containsExactly("line 1: packageLevel is not public in " + type + "; cannot be accessed from outside package");
        assertThat(Javac.messages("Probe", prefix + "public class Probe { int f(" + type + " v) { return v.protectedLevel; } }"))
                .containsExactly("line 1: protectedLevel has protected access in " + type);
        assertThat(Javac.messages("Probe", prefix + "public class Probe extends " + type + " { int f() { return protectedLevel; } }")).isEmpty();
        assertThat(Javac.messages("Probe", prefix + "public class Probe { int f(" + type + " v) { return v.publicLevel; } }")).isEmpty();
    }

    @Test
    void codeInTheSamePackageSeesPackagePrivateAndProtectedMembers() {
        Visibility visibility = new Visibility();
        assertThat(visibility.packageLevel).isEqualTo(2);
        assertThat(visibility.protectedLevel).isEqualTo(3);
        assertThat(visibility.publicLevel).isEqualTo(4);
        assertThat(visibility.viaOwnMethod()).isEqualTo(1);
    }
}
