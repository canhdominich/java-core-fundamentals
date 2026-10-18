package dev.canhdominich.core.regex;

import java.util.regex.Pattern;

public final class CompileReuse {

    private CompileReuse() {
    }

    public static int viaStringMatches(String[] inputs, int rounds) {
        int hits = 0;
        for (int r = 0; r < rounds; r++) {
            for (String input : inputs) {
                if (input.matches("[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+")) {
                    hits++;
                }
            }
        }
        return hits;
    }

    public static int viaCompiledPattern(String[] inputs, int rounds) {
        Pattern pattern = Pattern.compile("[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+");
        int hits = 0;
        for (int r = 0; r < rounds; r++) {
            for (String input : inputs) {
                if (pattern.matcher(input).matches()) {
                    hits++;
                }
            }
        }
        return hits;
    }

    public static void main(String[] args) {
        String[] inputs = {"an@example.com", "bad", "b.c@mail.co.uk", "x@"};
        int rounds = 100_000;
        viaStringMatches(inputs, 1000);
        viaCompiledPattern(inputs, 1000);
        long stringBest = Long.MAX_VALUE;
        long compiledBest = Long.MAX_VALUE;
        int a = 0;
        int b = 0;
        for (int i = 0; i < 3; i++) {
            long start = System.nanoTime();
            a = viaStringMatches(inputs, rounds);
            stringBest = Math.min(stringBest, System.nanoTime() - start);
            start = System.nanoTime();
            b = viaCompiledPattern(inputs, rounds);
            compiledBest = Math.min(compiledBest, System.nanoTime() - start);
        }
        System.out.println("matches counted by both ways = " + a + " and " + b);
        System.out.println("String.matches best of 3     = " + stringBest / 1_000_000 + " ms");
        System.out.println("compiled Pattern best of 3   = " + compiledBest / 1_000_000 + " ms");
        System.out.println("ratio                        = " + String.format("%.1f", (double) stringBest / compiledBest) + "x");
    }
}
