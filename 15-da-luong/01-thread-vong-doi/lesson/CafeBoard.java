public class CafeBoard {
    // Biến chung cho luồng mocha ghi và main đọc
    static int mochaCups;

    public static void main(String[] args) throws InterruptedException {
        Thread latte = new Thread(() -> System.out.println("brewing latte on " + Thread.currentThread().getName()), "latte-thread");
        latte.start();
        latte.join();
        System.out.println("step 1: " + latte.getState());

        Thread mocha = new Thread(() -> mochaCups = 2);
        mocha.start();
        mocha.join();
        System.out.println("step 2: cups " + mochaCups);

        Thread idle = new Thread(() -> {
            try {
                Thread.sleep(60_000);
            } catch (InterruptedException e) {
                System.out.println("woke up");
            }
        });
        idle.start();
        // Chờ tới khi idle thật sự đang ngủ
        while (idle.getState() != Thread.State.TIMED_WAITING) {
            Thread.sleep(1);
        }
        System.out.println("step 3: " + idle.getState());
        // Đánh thức luồng đang ngủ
        idle.interrupt();
        idle.join();
        System.out.println("step 4: " + idle.getState());
    }
}
