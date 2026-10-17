package dev.canhdominich.core.operators;

public class Bits {

    static final int READ = 1;
    static final int WRITE = 2;
    static final int EXECUTE = 4;

    static String bits(int value) {
        return String.format("%8s", Integer.toBinaryString(value & 0xFF)).replace(' ', '0');
    }

    public static boolean has(int permissions, int flag) {
        return (permissions & flag) != 0;
    }

    public static int remove(int permissions, int flag) {
        return permissions & ~flag;
    }

    public static void main(String[] args) {
        int permissions = READ | WRITE;
        System.out.println("READ | WRITE         = " + bits(permissions) + " (" + permissions + ")");
        System.out.println("has WRITE            = " + has(permissions, WRITE));
        System.out.println("has EXECUTE          = " + has(permissions, EXECUTE));
        System.out.println("remove WRITE         = " + bits(remove(permissions, WRITE)));
        System.out.println("toggle EXECUTE (^)   = " + bits(permissions ^ EXECUTE));
        System.out.println("12 & 10 = " + bits(12 & 10) + "  12 | 10 = " + bits(12 | 10) + "  12 ^ 10 = " + bits(12 ^ 10));
        System.out.println("5 << 1               = " + (5 << 1) + " (doubles)");
        System.out.println("-8 >> 1              = " + (-8 >> 1) + " (keeps the sign)");
        System.out.println("-8 >>> 1             = " + (-8 >>> 1) + " (fills with zeros)");
        System.out.println("1 << 31              = " + (1 << 31));
        System.out.println("1 << 32              = " + (1 << 32) + " (shift distance is taken modulo 32)");
        System.out.println("1 << 33              = " + (1 << 33));
        System.out.println("~5                   = " + (~5));
    }
}
