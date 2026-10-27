public class OrderFix {
    static final Object A = new Object();
    static final Object B = new Object();

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            // Mọi luồng lấy A trước rồi mới tới B nên không có vòng chờ
            synchronized (A) {
                synchronized (B) {
                }
            }
        };
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        t1.join(2000);
        t2.join(2000);
        System.out.println("both finished " + (!t1.isAlive() && !t2.isAlive()));
    }
}
