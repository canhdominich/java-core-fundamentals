package dev.canhdominich.core.oop.overview;

import java.util.List;

public class OverviewDemo {

    public static String objectsAreIndependent() {
        Account an = new Account("An", 1_000);
        Account binh = new Account("Binh", 1_000);
        an.deposit(500);
        return "An " + an.balance() + ", Binh " + binh.balance() + ", same object " + (an == binh);
    }

    public static String encapsulation() {
        Account account = new Account("An", 100);
        StringBuilder out = new StringBuilder();
        try {
            account.deposit(-5);
        } catch (IllegalArgumentException e) {
            out.append(e.getMessage());
        }
        try {
            account.withdraw(500);
        } catch (IllegalArgumentException e) {
            out.append(" | ").append(e.getMessage());
        }
        try {
            new Account("Chi", -1);
        } catch (IllegalArgumentException e) {
            out.append(" | ").append(e.getMessage());
        }
        out.append(" | balance still ").append(account.balance());
        return out.toString();
    }

    public static String polymorphism() {
        List<Account> accounts = List.of(new Account("An", 1_000), new SavingsAccount("Binh", 1_000, 2), new SavingsAccount("Chi", 5_000, 1));
        long total = 0;
        StringBuilder each = new StringBuilder();
        for (Account account : accounts) {
            long interest = account.monthEnd();
            total += interest;
            each.append(account.owner()).append('=').append(interest).append(' ');
        }
        return each.toString().trim() + " | total interest " + total;
    }

    public static String viaChannels(List<Channel> channels, String address) {
        StringBuilder out = new StringBuilder();
        int cost = 0;
        for (Channel channel : channels) {
            if (channel.accepts(address)) {
                out.append(channel.send(address, "hi")).append("; ");
                cost += channel.cost();
            }
        }
        return out.toString().trim() + " cost " + cost;
    }

    public static String proceduralUnknownChannel() {
        try {
            return ProceduralNotifier.send("fax", "x", "hi");
        } catch (IllegalArgumentException e) {
            return "runtime: " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        System.out.println("objectsAreIndependent = " + objectsAreIndependent());
        System.out.println("encapsulation         = " + encapsulation());
        System.out.println("polymorphism          = " + polymorphism());
        List<Channel> channels = List.of(new EmailChannel(), new SmsChannel(), new PushChannel());
        System.out.println("viaChannels (email)   = " + viaChannels(channels, "an@example.com"));
        System.out.println("viaChannels (phone)   = " + viaChannels(channels, "0912345678"));
        System.out.println("procedural, unknown   = " + proceduralUnknownChannel());
        System.out.println("procedural sms cost   = " + ProceduralNotifier.cost("sms"));
        String privateAccess = "class A { void f(dev.canhdominich.core.oop.overview.Account a) { a.balance = 5; } }";
        String missingMethod = "class Fax implements dev.canhdominich.core.oop.overview.Channel { public String send(String to, String text) { return text; } public int cost() { return 2; } }";
        String wrongType = "class A { void f() { dev.canhdominich.core.oop.overview.Channel c = new dev.canhdominich.core.oop.overview.Account(\"x\", 1); } }";
        System.out.println("private field from outside = " + Javac.messages("A", privateAccess));
        System.out.println("class missing a method     = " + Javac.messages("Fax", missingMethod));
        System.out.println("Account is not a Channel   = " + Javac.messages("A", wrongType));
    }
}
