package dev.canhdominich.core.memory;

public class SizeProbe {

    public static class Pair {
        int a;
        int b;
    }

    public static class Wide {
        long a;
        long b;
        long c;
    }

    private static long roundTo8(long bytes) {
        return (bytes + 4) / 8 * 8;
    }

    public static java.util.List<String> sizes() {
        int count = 200_000;
        java.util.List<String> out = new java.util.ArrayList<>();
        out.add("new Object()           = " + roundTo8(Child.bytesPerObject(Object::new, count)) + " bytes");
        out.add("new Pair (two ints)    = " + roundTo8(Child.bytesPerObject(Pair::new, count)) + " bytes");
        out.add("new Wide (three longs) = " + roundTo8(Child.bytesPerObject(Wide::new, count)) + " bytes");
        out.add("new int[0]             = " + roundTo8(Child.bytesPerObject(() -> new int[0], count)) + " bytes");
        out.add("new int[10]            = " + roundTo8(Child.bytesPerObject(() -> new int[10], count)) + " bytes");
        out.add("new byte[100]          = " + roundTo8(Child.bytesPerObject(() -> new byte[100], count)) + " bytes");
        out.add("new String(\"abc\")      = " + roundTo8(Child.bytesPerObject(() -> new String("abc"), count)) + " bytes (the characters are shared with the literal)");
        out.add("Long.valueOf(100000 + i) = " + roundTo8(Child.bytesPerObject(new java.util.function.Supplier<Object>() {
            long next = 100_000;

            @Override
            public Object get() {
                return Long.valueOf(next++);
            }
        }, count)) + " bytes");
        return out;
    }

    public static void main(String[] args) {
        for (String line : sizes()) {
            System.out.println(line);
        }
    }
}
