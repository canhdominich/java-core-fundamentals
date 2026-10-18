package dev.canhdominich.core.threads.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class UtilTest {

    @Test
    void aFixedPoolRunsAtMostItsSizeAtOnceAndQueuesTheRest() throws Exception {
        assertThat(Pools.fixedPoolQueuesTheExtraTasks()).isEqualTo("9 tasks on a pool of 3: finished true, most running at once 3, distinct threads used 3");
        assertThat(Pools.poolThreadsKeepTheirNames()).isEqualTo("task ran on billing-1");
    }

    @Test
    void shutdownRefusesNewTasksAndShutdownNowReturnsTheWaitingOnes() throws Exception {
        assertThat(Pools.submitAfterShutdown()).isEqualTo("RejectedExecutionException, isShutdown true");
        assertThat(Pools.shutdownNowReturnsThePending()).isEqualTo("shutdownNow returned 4 waiting tasks, the running task was interrupted true, terminated true");
    }

    @Test
    void futureCarriesValuesAndWrapsExceptions() throws Exception {
        assertThat(Futures.callableReturnsAValue()).isEqualTo("get returned 5, isDone true");
        assertThat(Futures.exceptionIsWrapped()).isEqualTo("get threw ExecutionException whose cause is IllegalStateException: price service down");
    }

    @Test
    void getCanTimeOutAndCancelInterruptsTheTask() throws Exception {
        assertThat(Futures.timeoutAndCancel()).isEqualTo("TimeoutException after 100 ms, cancel(true) true, isCancelled true, task interrupted true");
    }

    @Test
    void atomicsAndConcurrentMapsNeverLoseUpdates() throws Exception {
        assertThat(Safe.atomicCounters()).isEqualTo("expected 800000, AtomicInteger 800000, LongAdder 800000");
        assertThat(Safe.concurrentMapCountsExactly()).isEqualTo("expected 200000, ConcurrentHashMap.merge counted 200000");
        assertThat(Safe.compareAndSet()).isEqualTo("compareAndSet(10, 11) true, then compareAndSet(10, 12) false, value 11 before doubling, 22 after updateAndGet");
    }

    @Test
    void copyOnWriteListToleratesModificationWhileIterating() {
        assertThat(Safe.iterationWhileModifying()).isEqualTo("ArrayList: ConcurrentModificationException, CopyOnWriteArrayList: iterated [1, 2, 3] and now holds [1, 2, 3, 4]");
    }

    @Test
    void blockingQueueHandsOverEveryItemInOrder() throws Exception {
        assertThat(Safe.blockingQueue()).isEqualTo("consumer got [1, 2, 3, 4, 5, 6] through a queue of capacity 2");
    }

    @Test
    void latchSemaphoreAndBarrierCoordinateThreads() throws Exception {
        assertThat(Coordination.latchWaitsForWorkers()).isEqualTo("main resumed after 3 workers called countDown, remaining count 0");
        assertThat(Coordination.semaphoreLimitsConcurrency()).isEqualTo("6 threads, 2 permits: at most 2 inside at once, permits back to 2");
        assertThat(Coordination.barrierSynchronizesRounds()).isEqualTo("[round 1 complete, round 2 complete]");
    }

    @Test
    void completableFutureComposesRecoversAndChoosesItsPool() {
        assertThat(Futures.completableChain()).isEqualTo("total 360");
        assertThat(Futures.completableFailure()).isEqualTo("recovered with -1, join threw CompletionException wrapping IllegalArgumentException");
        assertThat(Futures.completableOnCustomPool()).isEqualTo("default pool thread starts with ForkJoinPool.commonPool-worker, custom pool thread orders-1");
        assertThat(Futures.allOf()).isEqualTo("allOf waited for all three, sum 6");
    }
}
