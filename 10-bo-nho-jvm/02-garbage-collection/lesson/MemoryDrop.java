public class MemoryDrop {
    static long usedMb() {
        Runtime rt = Runtime.getRuntime();
        return (rt.totalMemory() - rt.freeMemory()) / (1024 * 1024);
    }

    public static void main(String[] args) throws InterruptedException {
        byte[] stock = new byte[50 * 1024 * 1024];
        stock[0] = 1;
        long before = usedMb();
        System.out.println("used with stock: " + before + " MB");
        // Chỉ còn một phiếu mạnh, bỏ nó rồi đề nghị gom rác
        stock = null;
        System.gc();
        Thread.sleep(100);
        long after = usedMb();
        System.out.println("used after release: " + after + " MB");
        System.out.println("dropped by at least 25 MB: " + (before - after >= 25));
    }
}
