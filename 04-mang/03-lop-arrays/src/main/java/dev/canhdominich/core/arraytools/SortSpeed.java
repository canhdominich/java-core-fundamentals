package dev.canhdominich.core.arraytools;

import java.util.Arrays;
import java.util.Random;

public class SortSpeed {

    public static void main(String[] args) {
        int size = 2_000_000;
        Random random = new Random(42);
        int[] primitive = new int[size];
        Integer[] boxed = new Integer[size];
        for (int i = 0; i < size; i++) {
            primitive[i] = random.nextInt();
            boxed[i] = primitive[i];
        }
        long primitiveBest = Long.MAX_VALUE;
        long boxedBest = Long.MAX_VALUE;
        for (int round = 0; round < 3; round++) {
            int[] p = primitive.clone();
            long start = System.nanoTime();
            Arrays.sort(p);
            primitiveBest = Math.min(primitiveBest, System.nanoTime() - start);
            Integer[] b = boxed.clone();
            start = System.nanoTime();
            Arrays.sort(b);
            boxedBest = Math.min(boxedBest, System.nanoTime() - start);
        }
        System.out.println("elements                 = " + size);
        System.out.println("int[] best of 3          = " + primitiveBest / 1_000_000 + " ms");
        System.out.println("Integer[] best of 3      = " + boxedBest / 1_000_000 + " ms");
        System.out.println("Integer[] / int[]        = " + String.format("%.1f", (double) boxedBest / primitiveBest) + "x");
    }
}
