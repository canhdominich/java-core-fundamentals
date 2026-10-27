import java.lang.management.ManagementFactory;

public class SizeProbe {
    static class Pair {
        int a = 1;
        int b = 2;
    }

    public static void main(String[] args) {
        com.sun.management.ThreadMXBean bean = (com.sun.management.ThreadMXBean) ManagementFactory.getThreadMXBean();
        long id = Thread.currentThread().getId();
        Object[] keep = new Object[200_000];
        for (int i = 0; i < 1000; i++) {
            keep[i] = new Pair();
        }
        // Đếm byte luồng này cấp phát khi tạo 200000 đối tượng
        long before = bean.getThreadAllocatedBytes(id);
        for (int i = 0; i < keep.length; i++) {
            keep[i] = new Pair();
        }
        long after = bean.getThreadAllocatedBytes(id);
        System.out.println("Pair: " + (after - before) / keep.length + " bytes");
    }
}
