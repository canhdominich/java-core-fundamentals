package dev.canhdominich.core.jvm;

public class HotLoop {

    static long work(int rounds) {
        long total = 0;
        for (int i = 0; i < rounds; i++) {
            total += (i * 31L) % 7;
        }
        return total;
    }

    public static void main(String[] args) {
        int rounds = Integer.parseInt(args[0]);
        StringBuilder line = new StringBuilder();
        long sink = 0;
        for (int round = 1; round <= 5; round++) {
            long start = System.nanoTime();
            sink += work(rounds);
            line.append(String.format("%d ", (System.nanoTime() - start) / 1_000_000));
        }
        System.out.println(line.toString().trim() + " | " + sink);
    }
}
