package dev.canhdominich.core.memory;

public class MemoryDemo {

    public static String heapNumbers() {
        Runtime runtime = Runtime.getRuntime();
        long max = runtime.maxMemory() / (1024 * 1024);
        long total = runtime.totalMemory() / (1024 * 1024);
        return "max heap is larger than the committed heap or equal: " + (max >= total) + ", processors " + (runtime.availableProcessors() > 0);
    }

    public static String stackAndHeapByBehaviour() {
        int number = 5;
        int[] array = {5};
        change(number, array);
        return "int stays " + number + ", array element becomes " + array[0];
    }

    private static void change(int number, int[] array) {
        number = 99;
        array[0] = 99;
    }

    public static String limitedHeap() {
        return Child.run("dev.canhdominich.core.memory.OutOfMemoryProbe", "-Xmx64m", "-XX:+UseSerialGC");
    }

    public static String objectSizes() {
        return Child.run("dev.canhdominich.core.memory.SizeProbe", "-Xmx256m");
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("heap numbers      = " + heapNumbers());
        System.out.println("by behaviour      = " + stackAndHeapByBehaviour());
        int[] threads = ThreadStacks.run(1_000_000);
        System.out.println("each thread's own local variable: " + threads[0] + " and " + threads[1] + "; the shared array element is below 2000000: " + (threads[2] < 2_000_000)
                + " (lost updates: " + (2_000_000 - threads[2] > 0) + ")");
        System.out.println("heap limited to 64 MB in a child process:");
        System.out.println(limitedHeap());
        System.out.println("object sizes measured as bytes allocated per object:");
        System.out.println(objectSizes());
    }
}
