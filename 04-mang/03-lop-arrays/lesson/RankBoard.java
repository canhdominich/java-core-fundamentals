import java.util.Arrays;

public class RankBoard {
    public static void main(String[] args) {
        int[] scores = {70, 95, 40, 85, 60};
        // Chụp lại thứ tự cũ trước khi sort làm mất nó
        int[] original = scores.clone();
        // Sắp xếp tại chỗ, không trả về mảng mới
        Arrays.sort(scores);
        System.out.println("original " + Arrays.toString(original));
        System.out.println("sorted   " + Arrays.toString(scores));
        System.out.println("equals " + Arrays.equals(original, scores));
        System.out.println("find 85: " + Arrays.binarySearch(scores, 85));
        // Kết quả âm nghĩa là chưa có, và bằng trừ vị trí chèn rồi trừ 1
        System.out.println("find 50: " + Arrays.binarySearch(scores, 50));
    }
}
