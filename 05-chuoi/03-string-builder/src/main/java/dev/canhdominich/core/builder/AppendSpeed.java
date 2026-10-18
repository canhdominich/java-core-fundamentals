package dev.canhdominich.core.builder;

public class AppendSpeed {

    public static int withPlusEquals(int count) {
        String text = "";
        for (int i = 0; i < count; i++) {
            text += "x";
        }
        return text.length();
    }

    public static int withBuilder(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append("x");
        }
        return sb.length();
    }

    public static void main(String[] args) {
        int count = 50_000;
        withPlusEquals(2_000);
        withBuilder(2_000);
        long plusBest = Long.MAX_VALUE;
        long builderBest = Long.MAX_VALUE;
        int plusLength = 0;
        int builderLength = 0;
        for (int round = 0; round < 3; round++) {
            long start = System.nanoTime();
            plusLength = withPlusEquals(count);
            plusBest = Math.min(plusBest, System.nanoTime() - start);
            start = System.nanoTime();
            builderLength = withBuilder(count);
            builderBest = Math.min(builderBest, System.nanoTime() - start);
        }
        System.out.println("appends                 = " + count);
        System.out.println("both give the same text = " + (plusLength == builderLength));
        System.out.println("+= best of 3            = " + plusBest / 1_000_000 + " ms");
        System.out.println("StringBuilder best of 3 = " + String.format("%.2f", builderBest / 1_000_000.0) + " ms");
        System.out.println("ratio                   = " + (plusBest / Math.max(builderBest, 1)) + "x");
    }
}
