// Chạy cùng một hàm nhiều vòng để thấy tác dụng của biên dịch JIT
public class Warmup {
    static long work() {
        long sum = 0;
        // 20 triệu lần lặp đủ nặng để JVM coi đây là đoạn chạy nhiều
        for (int i = 0; i < 20_000_000; i++) {
            sum += i % 7;
        }
        return sum;
    }

    public static void main(String[] args) {
        for (int round = 1; round <= 5; round++) {
            long start = System.nanoTime();
            work();
            // Đổi nano giây sang mili giây
            long ms = (System.nanoTime() - start) / 1_000_000;
            System.out.println("round " + round + ": " + ms + " ms");
        }
    }
}
