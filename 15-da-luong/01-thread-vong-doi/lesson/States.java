import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class States {
    // Chờ tới đúng trạng thái thay vì đoán thời gian
    static void await(Thread t, Thread.State wanted) throws InterruptedException {
        while (t.getState() != wanted) {
            Thread.sleep(1);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        AtomicBoolean spin = new AtomicBoolean(true);
        Thread other = new Thread(() -> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
            }
        });
        Thread t = new Thread(() -> {
            // RUNNABLE: luồng đang làm việc
            while (spin.get()) {
                Thread.onSpinWait();
            }
            try {
                // TIMED_WAITING: chờ có giới hạn thời gian
                Thread.sleep(60_000);
            } catch (InterruptedException e) {
            }
            try {
                // WAITING: chờ luồng khác xong, không giới hạn
                other.join();
            } catch (InterruptedException e) {
            }
            synchronized (lock) {
            }
        });
        List<Thread.State> seen = new ArrayList<>();
        seen.add(t.getState());
        t.start();
        await(t, Thread.State.RUNNABLE);
        seen.add(t.getState());
        spin.set(false);
        await(t, Thread.State.TIMED_WAITING);
        seen.add(t.getState());
        other.start();
        synchronized (lock) {
            t.interrupt();
            await(t, Thread.State.WAITING);
            seen.add(t.getState());
            await(t, Thread.State.BLOCKED);
            seen.add(t.getState());
        }
        t.join();
        seen.add(t.getState());
        System.out.println(seen);
    }
}
