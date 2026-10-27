import java.lang.ref.WeakReference;

public class Breakages {
    public static void main(String[] args) throws InterruptedException {
        Object order = new byte[1024];
        WeakReference<Object> watcher = new WeakReference<>(order);
        // Gán null chỉ bỏ một phiếu, không dọn ngay
        order = null;
        System.out.println("right after null, no gc call: " + (watcher.get() != null));
        System.gc();
        Thread.sleep(100);
        try {
            Object again = watcher.get();
            // get() đã trả null nên dòng này ném NullPointerException
            System.out.println(again.hashCode());
        } catch (NullPointerException e) {
            System.out.println("use after collection: " + e.getClass().getSimpleName());
        }
    }
}
