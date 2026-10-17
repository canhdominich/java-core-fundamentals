package dev.canhdominich.core.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

public class ReadSpeed {

    static String numbers(int count) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < count; i++) {
            text.append(i).append('\n');
        }
        return text.toString();
    }

    public static long withScanner(String text) {
        Scanner scanner = new Scanner(text);
        long sum = 0;
        while (scanner.hasNextInt()) {
            sum += scanner.nextInt();
        }
        return sum;
    }

    public static long withBufferedReader(String text) throws IOException {
        BufferedReader reader = new BufferedReader(new StringReader(text));
        long sum = 0;
        String line;
        while ((line = reader.readLine()) != null) {
            sum += Integer.parseInt(line);
        }
        return sum;
    }

    static long millis(Runnable task) {
        long best = Long.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            long start = System.nanoTime();
            task.run();
            best = Math.min(best, (System.nanoTime() - start) / 1_000_000);
        }
        return best;
    }

    public static void main(String[] args) throws IOException {
        String text = numbers(1_000_000);
        long expected = withBufferedReader(text);
        System.out.println("sum of 1,000,000 numbers = " + expected + ", same with Scanner = " + (withScanner(text) == expected));
        long scanner = millis(() -> withScanner(text));
        long reader = millis(() -> {
            try {
                withBufferedReader(text);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        });
        System.out.println("Scanner         : " + scanner + " ms (best of 3)");
        System.out.println("BufferedReader  : " + reader + " ms (best of 3)");
        System.out.println("Scanner is about " + Math.round((double) scanner / reader) + " times slower");
    }
}
