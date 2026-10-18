package dev.canhdominich.core.exceptions.custom;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class CustomTest {

    @Test
    void aCheckedExceptionCarriesDataAboutTheFailure() {
        assertThat(CustomDemo.checkedException()).isEqualTo("caught: need 250 but only 100 available, shortfall 150, balance still 100");
    }

    @Test
    void anUncheckedExceptionReportsAProgrammingError() {
        assertThat(CustomDemo.uncheckedException()).isEqualTo("caught: amount must be positive: -5");
        Wallet wallet = new Wallet(10);
        assertThatThrownBy(() -> wallet.spend(0)).isInstanceOf(InvalidAmountException.class);
    }

    @Test
    void aSuccessfulCallNeedsNoCatchBlockInTheCallerOfTheService() {
        assertThat(CustomDemo.causeChain("7")).isEqualTo("found user-7");
    }

    @Test
    void eachLayerTranslatesTheFailureAndKeepsTheOriginalAsTheCause() {
        assertThat(CustomDemo.causeChain("500")).isEqualTo("ServiceException [cannot describe user 500] caused by StorageException [no row for id 500]");
        assertThat(CustomDemo.causeChain("abc")).isEqualTo(
                "ServiceException [cannot describe user abc] caused by StorageException [id column is not a number: abc] caused by NumberFormatException [For input string: \"abc\"]");
    }

    @Test
    void thePrintedStackTraceListsEveryCause() {
        assertThat(CustomDemo.stackTraceMentionsTheCause()).isEqualTo(
                "2 'Caused by' lines; first: Caused by: dev.canhdominich.core.exceptions.custom.StorageException: id column is not a number: abc");
    }

    @Test
    void rethrowingTheSameObjectKeepsItsOriginalStackButANewObjectStartsAFreshOne() {
        assertThat(Rethrow.rethrowTheSameObject()).isEqualTo("rethrown as is: top frame is lowLevel");
        assertThat(Rethrow.wrapInANewObject()).isEqualTo("wrapped without a cause: top frame is wrapInANewObject, cause null");
        assertThat(Rethrow.wrapWithCause()).isEqualTo("wrapped with a cause: top frame is wrapWithCause, cause frame is lowLevel");
    }

    @Test
    void theCompilerChecksThrowsClauses() {
        assertThat(Javac.messages("B", "class A { void f() { } } class B extends A { void f() throws Exception { } }"))
                .containsExactly("line 1: f() in B cannot override f() in A");
        assertThat(Javac.messages("B", "class A { void f() throws Exception { } } class B extends A { void f() { } }")).isEmpty();
        assertThat(Javac.messages("A", "class A { void g() throws Exception { } void f() { g(); } }"))
                .containsExactly("line 1: unreported exception java.lang.Exception; must be caught or declared to be thrown");
        assertThat(Javac.messages("A", "class A { void f() throws IllegalStateException { } void g() { f(); } }")).isEmpty();
    }
}
