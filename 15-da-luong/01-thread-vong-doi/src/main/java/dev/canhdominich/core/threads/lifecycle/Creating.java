package dev.canhdominich.core.threads.lifecycle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Creating {

    private Creating() {
    }

    static final class Worker extends Thread {
        private final List<String> log;

        Worker(String name, List<String> log) {
            super(name);
            this.log = log;
        }

        @Override
        public void run() {
            log.add("subclass ran on " + Thread.currentThread().getName());
        }
    }

    public static List<String> threeWays() throws InterruptedException {
        List<String> log = Collections.synchronizedList(new ArrayList<>());
        Thread a = new Worker("t-subclass", log);
        Thread b = new Thread(() -> log.add("runnable ran on " + Thread.currentThread().getName()), "t-runnable");
        Runnable task = () -> log.add("shared task ran on " + Thread.currentThread().getName());
        Thread c = new Thread(task, "t-first");
        Thread d = new Thread(task, "t-second");
        for (Thread t : List.of(a, b, c, d)) {
            t.start();
            t.join();
        }
        return log;
    }

    public static String startVersusRun() throws InterruptedException {
        List<String> names = Collections.synchronizedList(new ArrayList<>());
        Thread t = new Thread(() -> names.add(Thread.currentThread().getName()), "worker-1");
        t.run();
        String viaRun = names.get(0);
        names.clear();
        t.start();
        t.join();
        return "run() executed on " + viaRun + ", start() executed on " + names.get(0);
    }

    public static String startTwice() throws InterruptedException {
        Thread t = new Thread(() -> {
        }, "once");
        t.start();
        t.join();
        try {
            t.start();
            return "accepted";
        } catch (IllegalThreadStateException e) {
            return e.getClass().getSimpleName() + ", state is " + t.getState();
        }
    }

    public static String daemonRules() throws InterruptedException {
        Thread plain = new Thread(() -> {
        });
        Thread daemon = new Thread(() -> {
        });
        daemon.setDaemon(true);
        boolean[] inherited = new boolean[1];
        Thread parent = new Thread(() -> {
            Thread child = new Thread(() -> {
            });
            inherited[0] = child.isDaemon();
        });
        parent.setDaemon(true);
        parent.start();
        parent.join();
        Thread late = new Thread(() -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException ignored) {
            }
        });
        late.start();
        String lateResult;
        try {
            late.setDaemon(true);
            lateResult = "accepted";
        } catch (IllegalThreadStateException e) {
            lateResult = e.getClass().getSimpleName();
        }
        late.join();
        return "new thread daemon " + plain.isDaemon() + ", after setDaemon(true) " + daemon.isDaemon() + ", child of a daemon thread " + inherited[0] + ", setDaemon after start " + lateResult + ", main is daemon " + Thread.currentThread().isDaemon();
    }
}
