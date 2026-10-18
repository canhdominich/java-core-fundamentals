package dev.canhdominich.core.strings;

import java.util.Arrays;

public class StringBasics {

    public static String immutability() {
        String name = "java";
        String upper = name.toUpperCase();
        String replaced = name.replace('a', 'o');
        return "name " + name + ", toUpperCase " + upper + ", replace " + replaced + ", name again " + name;
    }

    public static String errors() {
        String text = "hello";
        StringBuilder out = new StringBuilder();
        try {
            text.charAt(5);
        } catch (StringIndexOutOfBoundsException e) {
            out.append("charAt(5): ").append(e.getMessage());
        }
        try {
            text.substring(2, 9);
        } catch (StringIndexOutOfBoundsException e) {
            out.append(" | substring(2, 9): ").append(e.getMessage());
        }
        try {
            text.substring(4, 2);
        } catch (StringIndexOutOfBoundsException e) {
            out.append(" | substring(4, 2): ").append(e.getMessage());
        }
        return out.toString();
    }

    public static String slicing() {
        String text = "hello world";
        return "substring(6) " + text.substring(6) + ", substring(0, 5) " + text.substring(0, 5) + ", substring(5, 5) [" + text.substring(5, 5)
                + "], indexOf(o) " + text.indexOf('o') + ", indexOf(o, 5) " + text.indexOf('o', 5) + ", lastIndexOf(o) " + text.lastIndexOf('o')
                + ", indexOf(z) " + text.indexOf('z') + ", contains(wor) " + text.contains("wor");
    }

    public static String replacing() {
        return "replace dot " + "a.b.c".replace(".", "-") + ", replaceAll dot " + "a.b.c".replaceAll(".", "-")
                + ", replaceAll escaped " + "a.b.c".replaceAll("\\.", "-") + ", replaceFirst " + "a.b.c".replaceFirst("\\.", "-");
    }

    public static String whitespace() {
        String padded = "  text  ";
        return "trim length " + padded.trim().length() + ", strip length " + padded.strip().length()
                + ", isBlank of spaces " + "   ".isBlank() + ", isEmpty of spaces " + "   ".isEmpty();
    }

    public static String splitting() {
        return "a,b,,".split(",").length + " parts for a,b,, | " + "a,b,,".split(",", -1).length + " with limit -1 | "
                + "".split(",").length + " part for the empty string | " + "a.b.c".split(".").length + " parts when splitting on a dot | "
                + "a.b.c".split("\\.").length + " parts when splitting on an escaped dot | " + Arrays.toString(" a  b ".split(" "))
                + " | " + Arrays.toString(" a  b ".trim().split("\\s+"));
    }

    public static String building() {
        char[] letters = {'j', 'a', 'v', 'a'};
        return "from char[] " + new String(letters) + ", valueOf(42) " + String.valueOf(42) + ", repeat " + "ab".repeat(3)
                + ", join " + String.join("-", "a", "b", "c") + ", toCharArray length " + "hello".toCharArray().length;
    }

    public static void main(String[] args) {
        System.out.println("immutability = " + immutability());
        System.out.println("errors       = " + errors());
        System.out.println("slicing      = " + slicing());
        System.out.println("replacing    = " + replacing());
        System.out.println("whitespace   = " + whitespace());
        System.out.println("splitting    = " + splitting());
        System.out.println("building     = " + building());
    }
}
