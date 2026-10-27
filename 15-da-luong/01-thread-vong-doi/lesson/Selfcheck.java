public class Selfcheck {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> System.out.println("B"));
        System.out.println("A");
        t.start();
        t.join();
        System.out.println("C");
    }
}
