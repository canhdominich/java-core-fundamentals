package dev.canhdominich.core.regex;

import java.util.regex.Pattern;

public final class Backtracking {

    private Backtracking() {
    }

    public static long millisFor(String regex, int length) {
        String input = "a".repeat(length) + "!";
        Pattern pattern = Pattern.compile(regex);
        long start = System.nanoTime();
        pattern.matcher(input).matches();
        return (System.nanoTime() - start) / 1_000_000;
    }

    public static void main(String[] args) {
        System.out.println("safe pattern a+b on 5000 characters        = " + millisFor("a+b", 5000) + " ms");
        System.out.println("nested quantifier (a+)+b on 28 characters = " + millisFor("(a+)+b", 28) + " ms (fast on this JDK)");
        for (int length = 12; length <= 36; length += 6) {
            System.out.println("eight repeated groups (.*a){8} on " + length + " characters = " + millisFor("(.*a){8}", length) + " ms");
        }
    }
}
