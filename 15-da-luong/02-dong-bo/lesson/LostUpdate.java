import java.util.concurrent.CyclicBarrier;

public class LostUpdate {
    static int count = 5;
    static final Object LOCK = new Object();

    static void addWithoutLock(CyclicBarrier bothRead) {
        try {
            int copy = count;
            // Ép hai luồng cùng đọc xong rồi mới được ghi: thứ tự xen kẽ cố định
            bothRead.await();
            count = copy + 1;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    static void addWithLock() {
        synchronized (LOCK) {
            int copy = count;
            count = copy + 1;
        }
    }

    static void runTwice(Runnable task) throws InterruptedException {
        Thread a = new Thread(task);
        Thread b = new Thread(task);
        a.start();
        b.start();
        a.join();
        b.join();
    }

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier bothRead = new CyclicBarrier(2);
        runTwice(() -> addWithoutLock(bothRead));
        System.out.println("no lock, both read 5 first: count is " + count);
        count = 5;
        runTwice(LostUpdate::addWithLock);
        System.out.println("with lock: count is " + count);
    }
}
