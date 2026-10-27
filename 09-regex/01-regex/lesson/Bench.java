import java.util.regex.Pattern;

public class Bench {
    public static void main(String[] args) {
        String[] phones = {"0912345678", "09123", "0987654321", "abc"};
        int rounds = 200_000;
        long best1 = Long.MAX_VALUE, best2 = Long.MAX_VALUE;
        Pattern compiled = Pattern.compile("\\d{10}");
        for (int run = 0; run < 3; run++) {
            long t = System.nanoTime();
            int c1 = 0;
            for (int i = 0; i < rounds; i++) {
                if (phones[i % 4].matches("\\d{10}")) c1++;
            }
            best1 = Math.min(best1, System.nanoTime() - t);
            t = System.nanoTime();
            int c2 = 0;
            for (int i = 0; i < rounds; i++) {
                if (compiled.matcher(phones[i % 4]).matches()) c2++;
            }
            best2 = Math.min(best2, System.nanoTime() - t);
            if (c1 != c2) throw new IllegalStateException();
        }
        System.out.println("String.matches " + best1 / 1_000_000 + " ms, compiled " + best2 / 1_000_000 + " ms");
    }
}
