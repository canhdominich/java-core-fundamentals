import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Handoff {
    private final Deque<Integer> items = new ArrayDeque<>();
    private final int capacity = 2;
    private int maxSeen;

    synchronized void put(int value) throws InterruptedException {
        // Luôn đặt wait trong vòng while và đứng trong khối synchronized
        while (items.size() == capacity) {
            wait();
        }
        items.addLast(value);
        maxSeen = Math.max(maxSeen, items.size());
        // Đánh thức các luồng đang chờ để chúng kiểm tra lại điều kiện
        notifyAll();
    }

    synchronized int take() throws InterruptedException {
        while (items.isEmpty()) {
            wait();
        }
        int value = items.removeFirst();
        notifyAll();
        return value;
    }

    public static void main(String[] args) throws InterruptedException {
        Handoff shelf = new Handoff();
        List<Integer> received = new ArrayList<>();
        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    shelf.put(i);
                }
            } catch (InterruptedException e) {
            }
        });
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    received.add(shelf.take());
                }
            } catch (InterruptedException e) {
            }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        System.out.println("received " + received + ", shelf never held more than " + shelf.maxSeen + " items");
        try {
            // Gọi wait mà không giữ khoá nên ném IllegalMonitorStateException
            new Object().wait();
        } catch (IllegalMonitorStateException e) {
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }
}
