import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class More {
    public static void main(String[] args) throws Exception {
        CompletableFuture<Integer> price = CompletableFuture.supplyAsync(() -> 100);
        CompletableFuture<Integer> tax = CompletableFuture.supplyAsync(() -> 20);
        // Ghép hai kết quả bất đồng bộ rồi nhân ba
        int total = price.thenCombine(tax, Integer::sum).thenApply(x -> x * 3).join();
        System.out.println("total " + total);

        CompletableFuture<Integer> broken = CompletableFuture.supplyAsync(() -> {
            throw new IllegalArgumentException("bad price");
        });
        System.out.println("recovered " + broken.exceptionally(e -> -1).join());
        try {
            broken.join();
        } catch (CompletionException e) {
            // join bọc lỗi gốc trong CompletionException
            System.out.println("join threw " + e.getClass().getSimpleName() + " with cause " + e.getCause().getClass().getSimpleName());
        }

        BlockingQueue<Integer> shelf = new ArrayBlockingQueue<>(2);
        List<Integer> got = new ArrayList<>();
        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 6; i++) {
                    // put chờ khi kệ đầy
                    shelf.put(i);
                }
            } catch (InterruptedException e) {
            }
        });
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 6; i++) {
                    // take chờ khi kệ rỗng
                    got.add(shelf.take());
                }
            } catch (InterruptedException e) {
            }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        System.out.println("consumer got " + got);

        Semaphore seats = new Semaphore(2);
        AtomicInteger inside = new AtomicInteger();
        AtomicInteger most = new AtomicInteger();
        List<Thread> guests = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Thread guest = new Thread(() -> {
                try {
                    seats.acquire();
                    most.accumulateAndGet(inside.incrementAndGet(), Math::max);
                    Thread.sleep(20);
                    inside.decrementAndGet();
                    seats.release();
                } catch (InterruptedException e) {
                }
            });
            guests.add(guest);
            guest.start();
        }
        for (Thread guest : guests) {
            guest.join();
        }
        System.out.println("at most 2 inside at once " + (most.get() <= 2) + ", permits back to " + seats.availablePermits());

        List<Integer> plain = new ArrayList<>(List.of(1, 2, 3));
        try {
            for (int n : plain) {
                if (n == 1) {
                    plain.add(4);
                }
            }
        } catch (java.util.ConcurrentModificationException e) {
            System.out.println("ArrayList: ConcurrentModificationException");
        }
        List<Integer> copy = new CopyOnWriteArrayList<>(List.of(1, 2, 3));
        List<Integer> seen = new ArrayList<>();
        for (int n : copy) {
            seen.add(n);
            if (n == 1) {
                copy.add(4);
            }
        }
        System.out.println("CopyOnWriteArrayList: iterated " + seen + " and now holds " + copy);
    }
}
