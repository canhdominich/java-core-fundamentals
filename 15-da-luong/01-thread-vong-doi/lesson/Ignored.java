public class Ignored {
    public static void main(String[] args) throws InterruptedException {
        Thread busy = new Thread(() -> {
            long end = System.nanoTime() + 300_000_000L;
            while (System.nanoTime() < end) {
                Thread.onSpinWait();
            }
        });
        busy.start();
        // Vòng lặp không đọc cờ nên lời nhắn bị phớt lờ
        busy.interrupt();
        busy.join();
        System.out.println("finished normally, state " + busy.getState());
    }
}
