package dev.canhdominich.core.strings;

public class Concatenation {

    public static String withNumbers() {
        return "1 + 2 + \"3\" = " + (1 + 2 + "3") + ", \"1\" + 2 + 3 = " + ("1" + 2 + 3) + ", \"1\" + (2 + 3) = " + ("1" + (2 + 3));
    }

    public static String withChars() {
        return "'a' + 'b' = " + ('a' + 'b') + ", \"\" + 'a' + 'b' = " + ("" + 'a' + 'b') + ", \"x\" + 'a' + 1 = " + ("x" + 'a' + 1);
    }

    public static String withNull() {
        String missing = null;
        return "\"value: \" + null = " + ("value: " + missing) + ", length " + ("value: " + missing).length();
    }

    public static String withDecimals() {
        return "\"x\" + 0.1 + 0.2 = " + ("x" + 0.1 + 0.2) + ", \"x\" + (0.1 + 0.2) = " + ("x" + (0.1 + 0.2));
    }

    public static String formatting() {
        return String.format("%s has %d items costing %.2f", "cart", 3, 19.5) + " | " + String.format("%5d|%-5d|%05d", 42, 42, 42)
                + " | " + "%d%%".formatted(50);
    }

    public static String plusEquals() {
        String result = "";
        for (int i = 1; i <= 5; i++) {
            result += i;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("numbers   = " + withNumbers());
        System.out.println("chars     = " + withChars());
        System.out.println("null      = " + withNull());
        System.out.println("decimals  = " + withDecimals());
        System.out.println("format    = " + formatting());
        System.out.println("plusEquals= " + plusEquals());
    }
}
