import java.util.regex.Pattern;

public class Slow {
    static long millisFor(String regex, int length) {
        String input = "a".repeat(length) + "!";
        Pattern pattern = Pattern.compile(regex);
        long start = System.nanoTime();
        pattern.matcher(input).matches();
        return (System.nanoTime() - start) / 1_000_000;
    }

    public static void main(String[] args) {
        for (int length = 12; length <= 36; length += 6) {
            System.out.println("(.*a){8} on " + length + " chars: " + millisFor("(.*a){8}", length) + " ms");
        }
    }
}
