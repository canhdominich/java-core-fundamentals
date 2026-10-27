import java.lang.management.ManagementFactory;
import java.util.concurrent.CountDownLatch;

public class Deadlock {
    static final Object A = new Object();
    static final Object B = new Object();

    // Lấy khoá first, rồi xin khoá second
    static void grab(Object first, Object second, CountDownLatch gate) {
        synchronized (first) {
            try {
                gate.countDown();
                // Chờ cả hai luồng cùng đã giữ khoá đầu, để deadlock chắc chắn xảy ra
                gate.await();
            } catch (InterruptedException e) {
                return;
            }
            synchronized (second) {
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch bothHold = new CountDownLatch(2);
        Thread t1 = new Thread(() -> grab(A, B, bothHold), "t1");
        // Thứ tự ngược lại: B rồi A
        Thread t2 = new Thread(() -> grab(B, A, bothHold), "t2");
        t1.setDaemon(true);
        t2.setDaemon(true);
        t1.start();
        t2.start();
        long[] ids = null;
        while (ids == null) {
            Thread.sleep(20);
            // JVM tự phát hiện vòng chờ nhau
            ids = ManagementFactory.getThreadMXBean().findDeadlockedThreads();
        }
        System.out.println("deadlocked threads " + ids.length + ", states " + t1.getState() + " and " + t2.getState());
    }
}
