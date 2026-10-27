public class Trace {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> System.out.println("A on " + Thread.currentThread().getName()), "w");
        System.out.println("1 " + t.getState());
        // Gọi thường nên in tên main, state vẫn NEW
        t.run();
        System.out.println("2 " + t.getState());
        t.start();
        t.join();
        System.out.println("3 " + t.getState());
    }
}
