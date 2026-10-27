import java.util.Arrays;

public class ClassGrades {
    public static void main(String[] args) {
        int[][] grades = {{8, 7, 9, 6}, {5, 9, 7, 8}, {10, 6, 8, 9}};
        System.out.println("step 1: " + grades.length + " " + grades[0].length + " " + grades[2][3]);

        int[] studentTotals = new int[grades.length];
        for (int r = 0; r < grades.length; r++) {
            for (int c = 0; c < grades[r].length; c++) {
                studentTotals[r] += grades[r][c];
            }
        }
        System.out.println("step 2: " + Arrays.toString(studentTotals));

        int[] subjectTotals = new int[grades[0].length];
        for (int r = 0; r < grades.length; r++) {
            for (int c = 0; c < grades[r].length; c++) {
                subjectTotals[c] += grades[r][c];
            }
        }
        System.out.println("step 3: " + Arrays.toString(subjectTotals));

        int top = 0;
        for (int r = 1; r < studentTotals.length; r++) {
            if (studentTotals[r] > studentTotals[top]) {
                top = r;
            }
        }
        System.out.println("step 4: student " + top + " total " + studentTotals[top]);

        int[][] copy = new int[grades.length][];
        for (int r = 0; r < grades.length; r++) {
            copy[r] = Arrays.copyOf(grades[r], grades[r].length);
        }
        copy[0][0] = 0;
        System.out.println("step 5: " + grades[0][0] + " " + copy[0][0]);
    }
}
