import java.util.Arrays;

public class Breakages {
    public static void main(String[] args) {
        int[] scores = {8, 6, 9, 7, 10};
        try {
            System.out.println(scores[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("index: " + e.getMessage());
        }
        try {
            System.out.println(scores[-1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("negative: " + e.getMessage());
        }
        int[] backup = scores;
        Arrays.sort(scores);
        System.out.println("alias: backup " + Arrays.toString(backup));
        String[] names = new String[3];
        names[0] = "An";
        try {
            System.out.println(names[1].length());
        } catch (NullPointerException e) {
            System.out.println("null: " + e.getMessage());
        }
        try {
            int[] bad = new int[-1];
        } catch (NegativeArraySizeException e) {
            System.out.println("negative size: " + e.getMessage());
        }
        System.out.println("print directly starts with [I@: " + scores.toString().startsWith("[I@"));
    }
}
