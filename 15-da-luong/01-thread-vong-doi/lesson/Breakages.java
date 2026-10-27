public class Breakages {
    public static void main(String[] args) throws InterruptedException {
        Thread once = new Thread(() -> {
        });
        once.start();
        once.join();
        try {
            once.start();
        } catch (IllegalThreadStateException e) {
            System.out.println("1: " + e + ", state " + once.getState());
        }

        // run() chạy trên luồng hiện tại
        Thread named = new Thread(() -> System.out.println("2: ran on " + Thread.currentThread().getName()), "worker-1");
        named.run();

        Thread crasher = new Thread(() -> {
            throw new IllegalStateException("boom");
        }, "crasher");
        // Lỗi chưa bắt chỉ làm chết luồng chứa nó
        crasher.setUncaughtExceptionHandler((t, e) -> System.out.println("3: " + t.getName() + " died with " + e));
        crasher.start();
        crasher.join();
        System.out.println("3: main is still running");
    }
}
