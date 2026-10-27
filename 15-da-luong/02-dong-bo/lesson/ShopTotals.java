public class ShopTotals {
    private int total;

    public synchronized void addSale(int cups) {
        total += cups;
    }

    public synchronized int total() {
        return total;
    }

    // Đọc và đặt về 0 trong cùng một lần giữ khoá
    public synchronized int reset() {
        int before = total;
        total = 0;
        return before;
    }

    public static void main(String[] args) throws InterruptedException {
        ShopTotals shop = new ShopTotals();
        Thread[] threads = new Thread[4];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int k = 0; k < 50_000; k++) {
                    shop.addSale(1);
                }
            });
            threads[i].start();
        }
        for (Thread t : threads) {
            t.join();
        }
        System.out.println(shop.total());
        System.out.println(shop.reset() + " " + shop.total());
    }
}
