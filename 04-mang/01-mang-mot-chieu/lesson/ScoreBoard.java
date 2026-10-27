import java.util.Arrays;

public class ScoreBoard {
    public static void main(String[] args) {
        // Tạo mảng 5 ô, mỗi ô tự mang giá trị mặc định 0
        int[] scores = new int[5];
        scores[0] = 8;
        scores[1] = 6;
        scores[2] = 9;
        System.out.println("length " + scores.length);
        System.out.println(Arrays.toString(scores));
        int total = 0;
        // Lấy ô đầu làm ứng viên điểm cao nhất để có cái mà so sánh
        int best = scores[0];
        // i chạy từ 0 đến length - 1, dùng dấu nhỏ hơn chứ không dùng nhỏ hơn hoặc bằng
        for (int i = 0; i < scores.length; i++) {
            total += scores[i];
            // Dấu lớn hơn nên khi hai điểm bằng nhau, điểm gặp trước được giữ
            if (scores[i] > best) {
                best = scores[i];
            }
        }
        System.out.println("total " + total + ", best " + best);
    }
}
