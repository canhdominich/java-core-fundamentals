import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class Leak {
    // Trường static là một gốc: món trong danh sách luôn còn phiếu dẫn tới
    static final List<Object> HISTORY = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        Object order = new byte[1024];
        WeakReference<Object> watcher = new WeakReference<>(order);
        HISTORY.add(order);
        order = null;
        System.gc();
        Thread.sleep(100);
        System.out.println("while in history: " + (watcher.get() != null));
        // Xoá danh sách thì mảng mới thật sự hết phiếu
        HISTORY.clear();
        System.gc();
        Thread.sleep(100);
        System.out.println("after clear: " + (watcher.get() != null));
    }
}
