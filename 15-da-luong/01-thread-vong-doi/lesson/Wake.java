public class Wake {
    public static void main(String[] args) throws InterruptedException {
        Thread sleeper = new Thread(() -> {
            try {
                // Đang ngủ nên bị interrupt() đánh thức ngay
                Thread.sleep(60_000);
            } catch (InterruptedException e) {
                // Cờ ngắt bị xoá khi InterruptedException được ném
                System.out.println("woke up: " + e.getMessage() + ", flag " + Thread.currentThread().isInterrupted());
            }
        });
        sleeper.start();
        // Chờ tới khi sleeper thật sự đang ngủ
        while (sleeper.getState() != Thread.State.TIMED_WAITING) {
            Thread.sleep(1);
        }
        sleeper.interrupt();
        sleeper.join();
        System.out.println("state " + sleeper.getState());
    }
}
