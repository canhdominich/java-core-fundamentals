package dev.canhdominich.core.threads.sync;

import java.util.List;

public final class SyncDemo {

    private SyncDemo() {
    }

    public static void main(String[] args) throws Exception {
        System.out.println("race                = " + Races.compare());
        System.out.println("reentrancy          = " + Races.reentrancy());
        System.out.println("volatile flag       = reader saw the change " + Visibility.volatileFlagIsSeen());
        System.out.println("plain flag          = reader saw the change " + Visibility.plainFlagIsSeen());
        System.out.println("producer consumer   = " + BoundedBuffer.produceAndConsume());
        System.out.println("wait without lock   = " + BoundedBuffer.waitWithoutLock());
        System.out.println("deadlock            = " + Deadlocks.opposingOrder());
        System.out.println("same lock order     = " + Deadlocks.sameOrder());
        System.out.println("tryLock back off    = " + Deadlocks.tryLockBackoff());
        List<String> mixed = Javac.messages("A", "class A { synchronized int x; }");
        List<String> staticSync = Javac.messages("B", "class B { void f() { synchronized (5) { } } }");
        List<String> notify = Javac.messages("C", "class C { void f() { Object o = new Object(); o.wait(); } }");
        System.out.println("compile field       = " + mixed);
        System.out.println("compile primitive   = " + staticSync);
        System.out.println("compile wait        = " + notify);
    }
}
