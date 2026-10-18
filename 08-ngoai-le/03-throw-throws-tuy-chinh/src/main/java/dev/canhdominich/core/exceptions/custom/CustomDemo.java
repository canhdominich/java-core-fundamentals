package dev.canhdominich.core.exceptions.custom;

import java.io.PrintWriter;
import java.io.StringWriter;

public class CustomDemo {

    public static String checkedException() {
        Wallet wallet = new Wallet(100);
        try {
            wallet.spend(250);
            return "spent";
        } catch (InsufficientFundsException e) {
            return "caught: " + e.getMessage() + ", shortfall " + e.shortfall() + ", balance still " + wallet.balance();
        }
    }

    public static String uncheckedException() {
        Wallet wallet = new Wallet(100);
        try {
            wallet.spend(-5);
            return "spent";
        } catch (InsufficientFundsException e) {
            return "wrong catch";
        } catch (InvalidAmountException e) {
            return "caught: " + e.getMessage();
        }
    }

    public static String causeChain(String rawId) {
        try {
            return new UserService().describe(rawId);
        } catch (ServiceException e) {
            StringBuilder chain = new StringBuilder();
            for (Throwable current = e; current != null; current = current.getCause()) {
                if (chain.length() > 0) {
                    chain.append(" caused by ");
                }
                chain.append(current.getClass().getSimpleName()).append(" [").append(current.getMessage()).append(']');
            }
            return chain.toString();
        }
    }

    public static String stackTraceMentionsTheCause() {
        try {
            new UserService().describe("abc");
            return "no exception";
        } catch (ServiceException e) {
            StringWriter text = new StringWriter();
            e.printStackTrace(new PrintWriter(text));
            int causedBy = 0;
            String firstCaused = "";
            for (String line : text.toString().split("\n")) {
                if (line.startsWith("Caused by:")) {
                    causedBy++;
                    if (firstCaused.isEmpty()) {
                        firstCaused = line;
                    }
                }
            }
            return causedBy + " 'Caused by' lines; first: " + firstCaused;
        }
    }

    public static void main(String[] args) {
        System.out.println("checked exception     = " + checkedException());
        System.out.println("unchecked exception   = " + uncheckedException());
        System.out.println("found                 = " + causeChain("7"));
        System.out.println("missing row           = " + causeChain("500"));
        System.out.println("not a number          = " + causeChain("abc"));
        System.out.println("stack trace           = " + stackTraceMentionsTheCause());
        System.out.println("rethrow               = " + Rethrow.rethrowTheSameObject());
        System.out.println("new exception         = " + Rethrow.wrapInANewObject());
        System.out.println("new exception + cause = " + Rethrow.wrapWithCause());
        String broaderThrows = "class A { void f() { } } class B extends A { void f() throws Exception { } }";
        String narrowerThrows = "class A { void f() throws Exception { } } class B extends A { void f() { } }";
        String unreported = "class A { void g() throws Exception { } void f() { g(); } }";
        String throwsUnchecked = "class A { void f() throws IllegalStateException { } void g() { f(); } }";
        String customNotThrowable = "class MyError { } class A { void f() { throw new MyError(); } }";
        String catchChecked = "class MyEx extends Exception { } class A { void f() { try { int x = 1; } catch (MyEx e) { } } }";
        System.out.println("override with broader throws   = " + Javac.messages("B", broaderThrows));
        System.out.println("override with narrower throws  = " + Javac.messages("B", narrowerThrows));
        System.out.println("call a method that throws      = " + Javac.messages("A", unreported));
        System.out.println("throws an unchecked exception  = " + Javac.messages("A", throwsUnchecked));
        System.out.println("throw a class that is no Throwable = " + Javac.messages("A", customNotThrowable));
        System.out.println("catch a checked type never thrown = " + Javac.messages("A", catchChecked));
    }
}
