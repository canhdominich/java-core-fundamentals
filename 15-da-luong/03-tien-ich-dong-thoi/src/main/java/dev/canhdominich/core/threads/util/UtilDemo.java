package dev.canhdominich.core.threads.util;

import java.util.List;

public final class UtilDemo {

    private UtilDemo() {
    }

    public static void main(String[] args) throws Exception {
        System.out.println("fixed pool          = " + Pools.fixedPoolQueuesTheExtraTasks());
        System.out.println("thread names        = " + Pools.poolThreadsKeepTheirNames());
        System.out.println("after shutdown      = " + Pools.submitAfterShutdown());
        System.out.println("shutdownNow         = " + Pools.shutdownNowReturnsThePending());
        System.out.println("callable            = " + Futures.callableReturnsAValue());
        System.out.println("wrapped exception   = " + Futures.exceptionIsWrapped());
        System.out.println("timeout and cancel  = " + Futures.timeoutAndCancel());
        System.out.println("atomic counters     = " + Safe.atomicCounters());
        System.out.println("compare and set     = " + Safe.compareAndSet());
        System.out.println("concurrent map      = " + Safe.concurrentMapCountsExactly());
        System.out.println("iteration           = " + Safe.iterationWhileModifying());
        System.out.println("blocking queue      = " + Safe.blockingQueue());
        System.out.println("latch               = " + Coordination.latchWaitsForWorkers());
        System.out.println("semaphore           = " + Coordination.semaphoreLimitsConcurrency());
        System.out.println("barrier             = " + Coordination.barrierSynchronizesRounds());
        System.out.println("completable chain   = " + Futures.completableChain());
        System.out.println("completable failure = " + Futures.completableFailure());
        System.out.println("completable pools   = " + Futures.completableOnCustomPool());
        System.out.println("all of              = " + Futures.allOf());
        List<String> pool = Javac.messages("A", "import java.util.concurrent.*;\nclass A { Future<String> f = Executors.newFixedThreadPool(2).submit(() -> 1); }");
        List<String> checked = Javac.messages("B", "import java.util.concurrent.*;\nclass B { void f(Future<String> x) { x.get(); } }");
        List<String> atomic = Javac.messages("C", "import java.util.concurrent.atomic.*;\nclass C { AtomicInteger a = 0; }");
        System.out.println("compile future type = " + pool);
        System.out.println("compile get         = " + checked);
        System.out.println("compile atomic      = " + atomic);
    }
}
