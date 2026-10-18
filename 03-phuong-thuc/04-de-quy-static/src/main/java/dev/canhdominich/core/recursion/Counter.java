package dev.canhdominich.core.recursion;

public class Counter {

    private static int created = 0;

    private int value;

    public Counter() {
        created++;
    }

    public void increment() {
        value++;
    }

    public int value() {
        return value;
    }

    public static int created() {
        return created;
    }

    public static void reset() {
        created = 0;
    }

    public String describe() {
        return "value " + value + " of " + created + " counters";
    }

    public static Counter withStart(int start) {
        Counter counter = new Counter();
        counter.value = start;
        return counter;
    }
}
