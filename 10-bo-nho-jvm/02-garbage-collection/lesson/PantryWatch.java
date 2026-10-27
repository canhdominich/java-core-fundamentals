import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class PantryWatch {
    static final List<Object> SHELF = new ArrayList<>();

    // Đề nghị gom rác rồi chờ một chút để quan sát
    static void collect() throws InterruptedException {
        System.gc();
        Thread.sleep(100);
    }

    public static void main(String[] args) throws InterruptedException {
        Object order = new byte[1024];
        WeakReference<Object> watcher = new WeakReference<>(order);
        collect();
        System.out.println("step 1: " + (watcher.get() != null));
        order = null;
        collect();
        System.out.println("step 2: " + (watcher.get() != null));

        Object first = new byte[1024];
        // Hai phiếu mạnh cùng chỉ vào một mảng
        Object second = first;
        WeakReference<Object> twin = new WeakReference<>(first);
        first = null;
        collect();
        System.out.println("step 3: " + (twin.get() != null));
        second = null;
        collect();
        System.out.println("step 4: " + (twin.get() != null));

        Object kept = new byte[1024];
        WeakReference<Object> shelfWatcher = new WeakReference<>(kept);
        // Danh sách static giữ phiếu nên mảng không bị dọn
        SHELF.add(kept);
        kept = null;
        collect();
        boolean onShelf = shelfWatcher.get() != null;
        SHELF.clear();
        collect();
        System.out.println("step 5: " + onShelf + " " + (shelfWatcher.get() != null));
    }
}
