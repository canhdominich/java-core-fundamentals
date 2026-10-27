public class Ticker {
    public static void main(String[] args) throws InterruptedException {
        Thread ticker = new Thread(() -> {
            int ticks = 0;
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // Bật lại cờ vì ném lỗi đã xoá nó
                    Thread.currentThread().interrupt();
                    break;
                }
                ticks++;
            }
            System.out.println("stopped, ticks at least 1: " + (ticks >= 1));
        });
        ticker.start();
        // Cho ticker chạy vài nhịp rồi mới ngắt
        Thread.sleep(350);
        ticker.interrupt();
        ticker.join();
        System.out.println("alive " + ticker.isAlive());
    }
}
