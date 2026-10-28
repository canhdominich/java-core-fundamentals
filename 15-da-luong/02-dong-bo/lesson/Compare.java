import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.function.Supplier;

public class Compare {
    interface Counter {
        void increment();
        int get();
    }

    // Không bảo vệ gì
    static class Plain implements Counter {
        private int count;
        public void increment() { count++; }
        public int get() { return count; }
    }

    // volatile chỉ đảm bảo nhìn thấy giá trị mới, không làm count++ nguyên tử
    static class Volatile implements Counter {
        private volatile int count;
        public void increment() { count++; }
        public int get() { return count; }
    }

    static class WrongLock implements Counter {
        private int count;
        public void increment() {
            // Mỗi lần một khoá mới nên không ai chung khoá với ai
            synchronized (new Object()) {
                count++;
            }
        }
        public int get() { return count; }
    }

    // Cùng khoá this cho cả hai phương thức
    static class Locked implements Counter {
        private int count;
        public synchronized void increment() { count++; }
        public synchronized int get() { return count; }
    }

    static int run(Supplier<Counter> factory, int threads, int perThread) throws InterruptedException {
        Counter counter = factory.get();
        // Cổng xuất phát để các luồng bắt đầu cùng lúc
        CountDownLatch go = new CountDownLatch(1);
        List<Thread> all = new ArrayList<>();
        for (int i = 0; i < threads; i++) {
            Thread t = new Thread(() -> {
                try {
                    go.await();
                } catch (InterruptedException e) {
                    return;
                }
                for (int k = 0; k < perThread; k++) {
                    counter.increment();
                }
            });
            all.add(t);
            t.start();
        }
        go.countDown();
        for (Thread t : all) {
            t.join();
        }
        return counter.get();
    }

    static final int RUNS = 20;

    public static void main(String[] args) throws InterruptedException {
        int expected = 4 * 200_000;
        int[] lostIn = new int[3];
        int[] last = new int[3];
        boolean aboveExpected = false;
        List<Supplier<Counter>> kinds = List.of(Plain::new, Volatile::new, WrongLock::new);
        for (int round = 0; round < RUNS; round++) {
            for (int k = 0; k < kinds.size(); k++) {
                int total = run(kinds.get(k), 4, 200_000);
                if (total < expected) lostIn[k]++;
                if (total > expected) aboveExpected = true;
                last[k] = total;
            }
        }
        int exact = run(Locked::new, 4, 200_000);
        System.out.println("synchronized method is exactly " + expected + ": " + (exact == expected));
        System.out.println("no variant ever went above " + expected + ": " + !aboveExpected);
        System.out.println("plain int lost updates at least once: " + (lostIn[0] > 0));
        System.out.println("volatile int lost updates at least once: " + (lostIn[1] > 0));
        System.out.println("new Object() as lock lost updates at least once: " + (lostIn[2] > 0));
        System.out.println("--- numbers below change on every run ---");
        System.out.println("plain int lost updates in " + lostIn[0] + " of " + RUNS + " runs, last total " + last[0]);
        System.out.println("volatile int lost updates in " + lostIn[1] + " of " + RUNS + " runs, last total " + last[1]);
        System.out.println("new Object() as lock lost updates in " + lostIn[2] + " of " + RUNS + " runs, last total " + last[2]);
    }
}
