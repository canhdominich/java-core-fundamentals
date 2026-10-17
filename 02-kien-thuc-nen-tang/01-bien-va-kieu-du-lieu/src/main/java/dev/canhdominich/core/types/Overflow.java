package dev.canhdominich.core.types;

public class Overflow {

    public static String addExactMessage() {
        try {
            return String.valueOf(Math.addExact(Integer.MAX_VALUE, 1));
        } catch (ArithmeticException e) {
            return e.getClass().getSimpleName() + ": " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        int max = Integer.MAX_VALUE;
        System.out.println("Integer.MAX_VALUE + 1        = " + (max + 1));
        System.out.println("Integer.MIN_VALUE - 1        = " + (Integer.MIN_VALUE - 1));
        System.out.println("(byte) 200                   = " + (byte) 200);
        System.out.println("(byte) 128                   = " + (byte) 128);
        System.out.println("(short) 40000                = " + (short) 40000);
        System.out.println("(int) 3_000_000_000L         = " + (int) 3_000_000_000L);
        System.out.println("(int) 3.99                   = " + (int) 3.99);
        System.out.println("(int) -3.99                  = " + (int) -3.99);
        System.out.println("(int) 1e20                   = " + (int) 1e20);
        System.out.println("1_000_000 * 1_000_000        = " + (1_000_000 * 1_000_000));
        System.out.println("1_000_000L * 1_000_000       = " + (1_000_000L * 1_000_000));
        System.out.println("Math.addExact(MAX, 1)        = " + addExactMessage());
    }
}
