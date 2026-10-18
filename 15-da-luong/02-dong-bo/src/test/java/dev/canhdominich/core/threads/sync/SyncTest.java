package dev.canhdominich.core.threads.sync;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SyncTest {

    private static final int THREADS = 4;
    private static final int PER_THREAD = 100_000;
    private static final int EXPECTED = THREADS * PER_THREAD;

    @Test
    void synchronizedMethodNeverLosesAnUpdate() throws Exception {
        assertThat(Races.run(Races.SyncMethod::new, THREADS, PER_THREAD)).isEqualTo(EXPECTED);
    }

    @Test
    void synchronizedBlockOnOneSharedLockNeverLosesAnUpdate() throws Exception {
        assertThat(Races.run(Races.SyncBlock::new, THREADS, PER_THREAD)).isEqualTo(EXPECTED);
    }

    @Test
    void unprotectedCountersCanOnlyLoseUpdatesNeverGainThem() throws Exception {
        assertThat(Races.run(Races.Unsafe::new, THREADS, PER_THREAD)).isLessThanOrEqualTo(EXPECTED);
        assertThat(Races.run(Races.VolatileCounter::new, THREADS, PER_THREAD)).isLessThanOrEqualTo(EXPECTED);
        assertThat(Races.run(Races.WrongLock::new, THREADS, PER_THREAD)).isLessThanOrEqualTo(EXPECTED);
    }

    @Test
    void aMonitorIsReentrant() {
        assertThat(Races.reentrancy()).isEqualTo("outer then inner on the same lock");
    }

    @Test
    void aVolatileFlagIsSeenByTheReader() throws Exception {
        assertThat(Visibility.volatileFlagIsSeen()).isTrue();
    }

    @Test
    void waitAndNotifyKeepTheBufferWithinItsCapacityAndInOrder() throws Exception {
        assertThat(BoundedBuffer.produceAndConsume()).isEqualTo("received [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], buffer never held more than 2 items");
    }

    @Test
    void waitOutsideSynchronizedIsRejected() {
        assertThat(BoundedBuffer.waitWithoutLock()).isEqualTo("IllegalMonitorStateException: current thread is not owner");
    }

    @Test
    void opposingLockOrderDeadlocksAndTheJvmCanSeeIt() throws Exception {
        assertThat(Deadlocks.opposingOrder()).startsWith("threads reported deadlocked 2");
    }

    @Test
    void aSingleLockOrderOrATimedTryLockAvoidsTheDeadlock() throws Exception {
        assertThat(Deadlocks.sameOrder()).isEqualTo("both threads finished true");
        assertThat(Deadlocks.tryLockBackoff()).startsWith("both finished true");
    }
}
