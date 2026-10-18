package dev.canhdominich.core.threads.sync;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public final class BoundedBuffer {

    private final Deque<Integer> items = new ArrayDeque<>();
    private final int capacity;
    private int maxSeen;

    public BoundedBuffer(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void put(int value) throws InterruptedException {
        while (items.size() == capacity) {
            wait();
        }
        items.addLast(value);
        maxSeen = Math.max(maxSeen, items.size());
        notifyAll();
    }

    public synchronized int take() throws InterruptedException {
        while (items.isEmpty()) {
            wait();
        }
        int value = items.removeFirst();
        notifyAll();
        return value;
    }

    public synchronized int maxSeen() {
        return maxSeen;
    }

    public static String produceAndConsume() throws InterruptedException {
        BoundedBuffer buffer = new BoundedBuffer(2);
        List<Integer> received = Collections.synchronizedList(new ArrayList<>());
        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    buffer.put(i);
                }
            } catch (InterruptedException ignored) {
            }
        });
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    received.add(buffer.take());
                }
            } catch (InterruptedException ignored) {
            }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        return "received " + received + ", buffer never held more than " + buffer.maxSeen() + " items";
    }

    public static String waitWithoutLock() {
        try {
            new Object().wait();
            return "accepted";
        } catch (IllegalMonitorStateException e) {
            return e.getClass().getSimpleName() + ": " + e.getMessage();
        } catch (InterruptedException e) {
            return "interrupted";
        }
    }
}
