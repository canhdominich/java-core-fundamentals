package dev.canhdominich.core.oop.overview;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class OverviewTest {

    @Test
    void everyObjectKeepsItsOwnState() {
        assertThat(OverviewDemo.objectsAreIndependent()).isEqualTo("An 1500, Binh 1000, same object false");
    }

    @Test
    void theClassGuardsItsOwnRules() {
        assertThat(OverviewDemo.encapsulation()).isEqualTo(
                "deposit must be positive: -5 | cannot withdraw 500 from 100 | opening balance must not be negative: -1 | balance still 100");
    }

    @Test
    void aSubclassObjectCanBeUsedWhereTheSuperclassIsExpected() {
        assertThat(OverviewDemo.polymorphism()).isEqualTo("An=0 Binh=20 Chi=50 | total interest 70");
    }

    @Test
    void savingsAccountsAddInterestToTheBalance() {
        SavingsAccount account = new SavingsAccount("An", 2_000, 5);
        assertThat(account.monthEnd()).isEqualTo(100);
        assertThat(account.balance()).isEqualTo(2_100);
        assertThat(new Account("Binh", 2_000).monthEnd()).isZero();
    }

    @Test
    void callersUseTheInterfaceAndNeverNameTheConcreteChannel() {
        List<Channel> channels = List.of(new EmailChannel(), new SmsChannel(), new PushChannel());
        assertThat(OverviewDemo.viaChannels(channels, "an@example.com")).isEqualTo("email to an@example.com: hi; push to an@example.com: hi; cost 1");
        assertThat(OverviewDemo.viaChannels(channels, "0912345678")).isEqualTo("sms to 0912345678: hi; push to 0912345678: hi; cost 300");
    }

    @Test
    void theProceduralVersionOnlyFailsAtRunTimeForAnUnknownChannel() {
        assertThat(OverviewDemo.proceduralUnknownChannel()).isEqualTo("runtime: unknown channel: fax");
        assertThat(ProceduralNotifier.cost("sms")).isEqualTo(300);
        assertThatThrownBy(() -> ProceduralNotifier.accepts("fax", "x")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void theCompilerEnforcesPrivateFieldsAndTheContractOfAnInterface() {
        assertThat(Javac.messages("A", "class A { void f(dev.canhdominich.core.oop.overview.Account a) { a.balance = 5; } }"))
                .containsExactly("line 1: balance has private access in dev.canhdominich.core.oop.overview.Account");
        assertThat(Javac.messages("Fax", "class Fax implements dev.canhdominich.core.oop.overview.Channel { public String send(String to, String text) { return text; } public int cost() { return 2; } }"))
                .hasSize(1)
                .allMatch(message -> message.contains("is not abstract and does not override abstract method accepts"));
    }

    @Test
    void anObjectOfTheWrongTypeCannotBeUsedAsAChannel() {
        assertThat(Javac.messages("A", "class A { void f() { dev.canhdominich.core.oop.overview.Channel c = new dev.canhdominich.core.oop.overview.Account(\"x\", 1); } }"))
                .hasSize(1)
                .allMatch(message -> message.startsWith("line 1: incompatible types"));
    }
}
