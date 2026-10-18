package dev.canhdominich.core.threads.lifecycle;

import java.util.List;

public final class LifecycleDemo {

    private LifecycleDemo() {
    }

    public static void main(String[] args) throws Exception {
        System.out.println("three ways          = " + Creating.threeWays());
        System.out.println("start vs run        = " + Creating.startVersusRun());
        System.out.println("start twice         = " + Creating.startTwice());
        System.out.println("daemon              = " + Creating.daemonRules());
        System.out.println("states              = " + States.walk());
        System.out.println("waiting kinds       = " + States.waitingKinds());
        System.out.println("join                = " + Stopping.joinSeesTheResult());
        System.out.println("interrupt sleeper   = " + Stopping.interruptASleeper());
        System.out.println("cooperative stop    = " + Stopping.cooperativeStop());
        System.out.println("ignored interrupt   = " + Stopping.ignoredInterrupt());
        System.out.println("uncaught            = " + Stopping.uncaught());
        List<String> notRunnable = Javac.messages("A", "class A { void f() { Thread t = new Thread(new Object()); } }");
        List<String> checked = Javac.messages("B", "class B { void f() { Thread.sleep(10); } }");
        List<String> overrideStart = Javac.messages("C", "class C extends Thread { public void run() throws Exception { } }");
        System.out.println("compile runnable    = " + notRunnable);
        System.out.println("compile sleep       = " + checked);
        System.out.println("compile run throws  = " + overrideStart);
    }
}
