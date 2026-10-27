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

    public static void main(String[] args) throws InterruptedException {
        int expected = 4 * 200_000;
        int plainLost = 0, volatileLost = 0, wrongLost = 0;
        for (int i = 0; i < 5; i++) {
            if (run(Plain::new, 4, 200_000) < expected) plainLost++;
            if (run(Volatile::new, 4, 200_000) < expected) volatileLost++;
            if (run(WrongLock::new, 4, 200_000) < expected) wrongLost++;
        }
        System.out.println("expected " + expected);
        System.out.println("synchronized method " + run(Locked::new, 4, 200_000));
        System.out.println("plain int lost updates in " + plainLost + " of 5 runs");
        System.out.println("volatile int lost updates in " + volatileLost + " of 5 runs");
        System.out.println("new Object() as lock lost updates in " + wrongLost + " of 5 runs");
    }
}
