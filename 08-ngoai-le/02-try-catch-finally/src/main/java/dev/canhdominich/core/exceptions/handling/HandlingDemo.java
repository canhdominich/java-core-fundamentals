package dev.canhdominich.core.exceptions.handling;

public class HandlingDemo {

    public static void main(String[] args) {
        System.out.println("input 5          = " + Flow.run("5"));
        System.out.println("input abc        = " + Flow.run("abc"));
        System.out.println("input 0          = " + Flow.run("0"));
        System.out.println("finally and an uncaught exception = " + Flow.uncaught());
        Trace.clear();
        String returned = Flow.returnInFinally();
        System.out.println("finally and return = " + returned + ", trace " + Trace.steps());
        System.out.println("return value captured = " + Flow.returnValueIsCaptured());
        System.out.println("return inside finally = " + Flow.finallyOverrides());
        System.out.println("swallowed          = parseOrZero(\"abc\") " + Flow.parseOrZero("abc") + ", parseOrZero(\"0\") " + Flow.parseOrZero("0")
                + " (indistinguishable)");
        try {
            Flow.parseOrReport("abc");
        } catch (IllegalArgumentException e) {
            System.out.println("wrapped            = " + e.getMessage() + ", cause " + e.getCause().getClass().getSimpleName());
        }
        System.out.println("multi-catch        = " + Flow.multiCatch("x") + " | " + Flow.multiCatch(null) + " | " + Flow.multiCatch("7"));
        System.out.println("closing order      = " + Resources.orderOfClosing());
        System.out.println("closed on failure  = " + Resources.closedEvenWhenTheBodyFails());
        System.out.println("suppressed         = " + Resources.suppressed());
        Trace.clear();
        System.out.println("manual finally, swallowing = " + Resources.manualFinallySwallowingTheCloseFailure() + ", trace " + Trace.steps());
        System.out.println("manual finally, close throws = " + Resources.manualFinallyThrowing());
        String tryAlone = "class A { void f() { try { int x = 1; } } }";
        String catchAlone = "class A { void f() { catch (Exception e) { } } }";
        String relatedAlternatives = "class A { void f() { try { int x = 1; } catch (RuntimeException | IllegalStateException e) { } } }";
        String assignMulti = "class A { void f() { try { int x = Integer.parseInt(\"1\"); } catch (NumberFormatException | NullPointerException e) { e = null; } } }";
        String notCloseable = "class A { void f() { try (String s = \"x\") { } } }";
        System.out.println("try alone                  = " + Javac.messages("A", tryAlone));
        System.out.println("catch alone                = " + Javac.messages("A", catchAlone));
        System.out.println("related multi-catch types  = " + Javac.messages("A", relatedAlternatives));
        System.out.println("assign a multi-catch param = " + Javac.messages("A", assignMulti));
        System.out.println("resource not AutoCloseable = " + Javac.messages("A", notCloseable));
    }
}
