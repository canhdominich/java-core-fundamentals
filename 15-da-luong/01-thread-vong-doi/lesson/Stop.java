public class Stop {
    public static void main(String[] args) throws InterruptedException {
        Thread worker = new Thread(() -> {
            // Đọc cờ ngắt ở đầu mỗi vòng, đây là chỗ luồng đọc lời nhắn
            while (!Thread.currentThread().isInterrupted()) {
                Thread.onSpinWait();
            }
            System.out.println("saw the flag, stopping");
        }, "worker");
        worker.start();
        Thread.sleep(50);
        // Chỉ bật cờ, worker tự quyết định thoát
        worker.interrupt();
        worker.join();
        System.out.println("alive " + worker.isAlive());
    }
}
