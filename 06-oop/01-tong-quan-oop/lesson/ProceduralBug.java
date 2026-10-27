import java.util.Arrays;

public class ProceduralBug {
    public static void main(String[] args) {
        String[] names = {"Binh", "An"};
        int[] points = {5, 30};
        // Chỉ sắp mảng tên, mảng điểm đứng yên nên cặp bị lệch
        Arrays.sort(names);
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i] + " has " + points[i] + " points");
        }
    }
}
