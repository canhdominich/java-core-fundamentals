public class Barista {
    // Biến chung nằm trên đống nhớ, mọi luồng cùng thấy
    static int cups;

    public static void main(String[] args) throws InterruptedException {
        Runnable brew = () -> {
            System.out.println("brewing on " + Thread.currentThread().getName());
            cups = 3;
        };
        Thread barista = new Thread(brew, "barista-1");
        System.out.println("state " + barista.getState());
        // Gọi thường: chạy ngay trên luồng main, không có luồng mới
        barista.run();
        System.out.println("after run: cups " + cups + ", state " + barista.getState());
        cups = 0;
        // Tạo luồng thật, main đi tiếp ngay
        barista.start();
        // Main chờ barista-1 xong rồi mới đọc cups
        barista.join();
        System.out.println("after join: cups " + cups + ", state " + barista.getState());
    }
}
