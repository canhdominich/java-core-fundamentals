package dev.canhdominich.core.builder;

import java.util.List;

public class BuilderOps {

    public static String chaining() {
        StringBuilder sb = new StringBuilder();
        sb.append("Java").append(' ').append(17).append(' ').append(true).append(' ').append(1.5).append(' ').append((Object) null);
        return sb.toString();
    }

    public static String editing() {
        StringBuilder sb = new StringBuilder("hello world");
        sb.insert(0, ">> ");
        sb.replace(3, 8, "HELLO");
        sb.delete(sb.length() - 6, sb.length());
        sb.append('!');
        sb.setCharAt(3, 'J');
        return sb.toString();
    }

    public static String reversing(String text) {
        return new StringBuilder(text).reverse().toString();
    }

    public static boolean isPalindrome(String text) {
        String cleaned = text.toLowerCase().replaceAll("[^a-z0-9]", "");
        return new StringBuilder(cleaned).reverse().toString().equals(cleaned);
    }

    public static String joinWithSeparator(List<String> items) {
        StringBuilder sb = new StringBuilder();
        for (String item : items) {
            sb.append(item).append(", ");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }

    public static String equalityTraps() {
        StringBuilder first = new StringBuilder("abc");
        StringBuilder second = new StringBuilder("abc");
        return "equals " + first.equals(second) + ", same text " + first.toString().equals(second.toString())
                + ", compareTo " + first.compareTo(second) + ", contentEquals " + "abc".contentEquals(first);
    }

    public static String deleteAndInsertErrors() {
        StringBuilder sb = new StringBuilder("abc");
        StringBuilder out = new StringBuilder();
        try {
            sb.insert(5, "x");
        } catch (StringIndexOutOfBoundsException e) {
            out.append("insert(5): ").append(e.getMessage());
        }
        try {
            sb.deleteCharAt(3);
        } catch (StringIndexOutOfBoundsException e) {
            out.append(" | deleteCharAt(3): ").append(e.getMessage());
        }
        sb.delete(1, 99);
        out.append(" | delete(1, 99) is allowed: ").append(sb);
        return out.toString();
    }

    public static String setLengthPadsWithNulChars() {
        StringBuilder sb = new StringBuilder("abc");
        sb.setLength(5);
        int nuls = 0;
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '\0') {
                nuls++;
            }
        }
        StringBuilder shorter = new StringBuilder("abcdef");
        shorter.setLength(2);
        return "length " + sb.length() + " with " + nuls + " NUL characters, truncated " + shorter;
    }

    public static void main(String[] args) {
        System.out.println("chaining            = " + chaining());
        System.out.println("editing             = " + editing());
        System.out.println("reversing           = " + reversing("Java"));
        System.out.println("palindromes         = " + isPalindrome("A man, a plan, a canal: Panama") + ", " + isPalindrome("Java"));
        System.out.println("joinWithSeparator   = " + joinWithSeparator(List.of("An", "Binh", "Chi")) + " | empty list [" + joinWithSeparator(List.of()) + "]");
        System.out.println("equality traps      = " + equalityTraps());
        System.out.println("errors              = " + deleteAndInsertErrors());
        System.out.println("setLength           = " + setLengthPadsWithNulChars());
    }
}
