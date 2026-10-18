package dev.canhdominich.core.iface.basics;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class InterfaceTest {

    @Test
    void oneMethodWorksForEveryKindOfPayable() {
        assertThat(InterfaceDemo.oneRunnerManyKinds()).isEqualTo("total 3285000, over the limit: Big Co");
        assertThat(PaymentRun.total(List.of())).isZero();
    }

    @Test
    void aClassCanImplementSeveralInterfacesAndBeUsedThroughEach() {
        assertThat(InterfaceDemo.severalInterfaces()).isEqualTo(
                "as Payable: An 1200, as Exportable: An,1000,200, instanceof Payable true, instanceof Exportable true, a SupplierInvoice is Exportable false");
    }

    @Test
    void aFixedClockMakesTimeDependentCodeDeterministic() {
        assertThat(InterfaceDemo.substitutableClock()).isEqualTo(
                "fixed clock gives a stable receipt: paid 110 to Acme at 1700000000000; real clock starts with the same words: true");
        assertThat(new Receipt(new FixedClock(5)).issue(new Salary("An", 1, 1))).isEqualTo("paid 2 to An at 5");
    }

    @Test
    void anAnonymousClassCanImplementAnInterfaceOnTheSpot() {
        assertThat(InterfaceDemo.anonymousImplementation()).isEqualTo("waiter 15 within limit true");
    }

    @Test
    void interfaceFieldsAreConstantsSharedByImplementers() {
        assertThat(InterfaceDemo.constantsAreStatic()).containsExactly("Payable.MAX_AMOUNT 1000000", "same value through an implementing class 1000000");
    }

    @Test
    void theCompilerEnforcesTheContractOfAnInterface() {
        assertThat(Javac.messages("A", "interface I { void f(); } class A { I i = new I(); }")).containsExactly("line 1: I is abstract; cannot be instantiated");
        assertThat(Javac.messages("A", "interface I { void f(); } class A implements I { void f() { } }"))
                .containsExactly("line 1: f() in A cannot implement f() in I");
        assertThat(Javac.messages("A", "interface I { void f(); void g(); } class A implements I { public void f() { } }"))
                .containsExactly("line 1: A is not abstract and does not override abstract method g() in I");
        assertThat(Javac.messages("A", "interface I { int X = 1; } class A { void f() { I.X = 2; } }"))
                .containsExactly("line 1: cannot assign a value to final variable X");
        assertThat(Javac.messages("A", "interface I { } class A extends I { }")).containsExactly("line 1: no interface expected here");
        assertThat(Javac.messages("A", "class B { } class A implements B { }")).containsExactly("line 1: interface expected here");
    }
}
