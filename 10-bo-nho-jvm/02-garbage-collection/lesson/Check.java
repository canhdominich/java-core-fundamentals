import java.lang.ref.WeakReference;

public class Check {
    static class Node {
        Node other;
        byte[] payload = new byte[1024];
    }

    static void collect() throws InterruptedException {
        System.gc();
        Thread.sleep(100);
    }

    public static void main(String[] args) throws InterruptedException {
        Node a = new Node();
        Node b = new Node();
        Node c = new Node();
        a.other = b;
        b.other = c;
        c.other = a;
        WeakReference<Node> wa = new WeakReference<>(a);
        WeakReference<Node> wb = new WeakReference<>(b);
        WeakReference<Node> wc = new WeakReference<>(c);
        a = null;
        b = null;
        collect();
        System.out.println("after a, b null: " + (wa.get() != null) + " " + (wb.get() != null) + " " + (wc.get() != null));
        c = null;
        collect();
        System.out.println("after c null: " + (wa.get() != null) + " " + (wb.get() != null) + " " + (wc.get() != null));
        Object x = new Object();
        Object y = x;
        WeakReference<Object> w = new WeakReference<>(x);
        x = null;
        collect();
        System.out.println("y holds: " + (w.get() != null));
        y = null;
        collect();
        System.out.println("y released: " + (w.get() != null));
    }
}
