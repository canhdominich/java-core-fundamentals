public class Station implements Runnable {
    private final int orders;
    private int cups;

    public Station(int orders) {
        this.orders = orders;
    }

    @Override
    public void run() {
        try {
            // Giả lập thời gian pha
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // Bị ngắt thì thoát luôn
            return;
        }
        // Mỗi luồng chỉ ghi trường riêng của quầy mình
        cups = orders;
    }

    public int cups() {
        return cups;
    }

    public static void main(String[] args) throws InterruptedException {
        Station a = new Station(3);
        Station b = new Station(4);
        Thread ta = new Thread(a, "station-a");
        Thread tb = new Thread(b, "station-b");
        ta.start();
        tb.start();
        ta.join();
        tb.join();
        System.out.println("states " + ta.getState() + " " + tb.getState());
        System.out.println("total " + (a.cups() + b.cups()));
    }
}
