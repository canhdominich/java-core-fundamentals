package dev.canhdominich.core.threads.lifecycle;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class LifecycleTest {

    @Test
    void everyCreationStyleRunsOnItsOwnNamedThread() throws Exception {
        assertThat(Creating.threeWays()).containsExactly("subclass ran on t-subclass", "runnable ran on t-runnable", "shared task ran on t-first", "shared task ran on t-second");
    }

    @Test
    void runStaysOnTheCallerAndStartMovesToANewThread() throws Exception {
        assertThat(Creating.startVersusRun()).isEqualTo("run() executed on main, start() executed on worker-1");
    }

    @Test
    void aThreadCannotBeStartedTwice() throws Exception {
        assertThat(Creating.startTwice()).isEqualTo("IllegalThreadStateException, state is TERMINATED");
    }

    @Test
    void daemonStatusIsInheritedAndFrozenAtStart() throws Exception {
        assertThat(Creating.daemonRules()).contains("new thread daemon false").contains("child of a daemon thread true").contains("setDaemon after start IllegalThreadStateException").contains("main is daemon false");
    }

    @Test
    void aThreadVisitsEverySixState() throws Exception {
        assertThat(States.walk()).containsExactly(Thread.State.NEW, Thread.State.RUNNABLE, Thread.State.TIMED_WAITING, Thread.State.WAITING, Thread.State.BLOCKED, Thread.State.TERMINATED);
        assertThat(States.waitingKinds()).isEqualTo("wait() WAITING, wait(ms) TIMED_WAITING");
    }

    @Test
    void joinMakesTheResultVisible() throws Exception {
        assertThat(Stopping.joinSeesTheResult()).endsWith("after join result 42 alive false");
    }

    @Test
    void interruptWakesASleeperAndClearsTheFlag() throws Exception {
        assertThat(Stopping.interruptASleeper()).isEqualTo("caught InterruptedException with message 'sleep interrupted', interrupt flag inside catch false, state TERMINATED");
    }

    @Test
    void interruptIsOnlyARequestThatTheCodeMustHonour() throws Exception {
        assertThat(Stopping.cooperativeStop()).contains("loop saw the flag true").contains("still alive false");
        assertThat(Stopping.ignoredInterrupt()).contains("finished normally true");
    }

    @Test
    void anUncaughtExceptionKillsOnlyItsOwnThread() throws Exception {
        List<String> parts = List.of(Stopping.uncaught().split(", "));
        assertThat(parts).containsExactly("crasher died with IllegalStateException: boom", "state TERMINATED", "main still running true");
    }
}
