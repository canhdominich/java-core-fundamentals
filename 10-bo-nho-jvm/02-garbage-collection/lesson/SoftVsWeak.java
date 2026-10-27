import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class SoftVsWeak {
    public static void main(String[] args) throws InterruptedException {
        Object a = new byte[1024];
        Object b = new byte[1024];
        SoftReference<Object> soft = new SoftReference<>(a);
        WeakReference<Object> weak = new WeakReference<>(b);
        // Bỏ cả hai phiếu mạnh rồi đề nghị gom rác
        a = null;
        b = null;
        System.gc();
        Thread.sleep(100);
        System.out.println("soft: " + (soft.get() != null));
        System.out.println("weak: " + (weak.get() != null));
    }
}
