public class Daemon {
    public static void main(String[] args) throws InterruptedException {
        Thread plain = new Thread(() -> {
        });
        System.out.println("new thread daemon " + plain.isDaemon());
        // setDaemon chỉ gọi được trước start()
        plain.setDaemon(true);
        System.out.println("after setDaemon(true) " + plain.isDaemon());
        Thread late = new Thread(() -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
        });
        late.start();
        try {
            // Sau start() sẽ ném IllegalThreadStateException
            late.setDaemon(true);
        } catch (IllegalThreadStateException e) {
            System.out.println("setDaemon after start: " + e.getClass().getSimpleName());
        }
        late.join();
    }
}
