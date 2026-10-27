import java.lang.ref.WeakReference;

public class GcWatch {
    public static void main(String[] args) throws InterruptedException {
        Object order = new byte[1024];
        // Tham chiếu yếu: nhìn thấy mảng nhưng không giữ nó sống
        WeakReference<Object> watcher = new WeakReference<>(order);
        // System.gc() chỉ là lời đề nghị, máy ảo có thể bỏ qua
        System.gc();
        Thread.sleep(100);
        System.out.println("while held: " + (watcher.get() != null));
        // Bỏ phiếu mạnh cuối cùng: mảng đủ điều kiện bị dọn
        order = null;
        System.gc();
        Thread.sleep(100);
        System.out.println("after release: " + (watcher.get() != null));
    }
}
