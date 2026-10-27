import java.lang.ref.WeakReference;

public class Island {
    static class Node {
        Node other;
        byte[] payload = new byte[1024];
    }

    public static void main(String[] args) throws InterruptedException {
        Node a = new Node();
        Node b = new Node();
        // Hai nút trỏ vào nhau tạo thành chu trình
        a.other = b;
        b.other = a;
        WeakReference<Node> watchA = new WeakReference<>(a);
        WeakReference<Node> watchB = new WeakReference<>(b);
        // Không còn biến nào từ gốc dẫn tới hai nút
        a = null;
        b = null;
        System.gc();
        Thread.sleep(100);
        System.out.println("A alive: " + (watchA.get() != null));
        System.out.println("B alive: " + (watchB.get() != null));
    }
}
