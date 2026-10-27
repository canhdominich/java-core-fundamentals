import java.util.Arrays;

public class ClassScores {
    public static void main(String[] args) {
        int[] scores = {8, 6, 9, 7, 10};
        System.out.println("step 1: " + scores.length + " " + scores[0] + " " + scores[scores.length - 1]);

        int total = 0;
        for (int i = 0; i < scores.length; i++) {
            total += scores[i];
        }
        double average = (double) total / scores.length;
        System.out.println("step 2: total " + total + " average " + average);

        int[] backup = Arrays.copyOf(scores, scores.length);
        Arrays.sort(scores);
        System.out.println("step 3: scores " + Arrays.toString(scores) + " backup " + Arrays.toString(backup));

        int high = 0;
        for (int score : scores) {
            if (score >= 8) {
                high++;
            }
        }
        System.out.println("step 4: " + high);

        int[] bigger = Arrays.copyOf(scores, 7);
        System.out.println("step 5: " + Arrays.toString(bigger));
    }
}
