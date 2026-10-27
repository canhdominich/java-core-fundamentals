import java.util.Arrays;

public class ScoreTools {
    public static void main(String[] args) {
        int[] scores = {70, 95, 40, 85, 60};
        System.out.println("step 1: " + Arrays.toString(scores));

        int[] ranked = scores.clone();
        Arrays.sort(ranked);
        System.out.println("step 2: scores " + Arrays.toString(scores) + " ranked " + Arrays.toString(ranked));

        int[] part = {9, 8, 7, 6, 5};
        Arrays.sort(part, 1, 4);
        System.out.println("step 3: " + Arrays.toString(part));

        int found = Arrays.binarySearch(ranked, 70);
        int missing = Arrays.binarySearch(ranked, 65);
        int insertAt = -missing - 1;
        System.out.println("step 4: " + found + " " + missing + " insert at " + insertAt);

        int[] a = new int[3];
        Arrays.fill(a, 7);
        int[] b = {7, 7, 7};
        System.out.println("step 5: " + (a == b) + " " + Arrays.equals(a, b));
    }
}
