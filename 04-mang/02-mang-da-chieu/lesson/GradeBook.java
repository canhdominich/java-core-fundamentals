import java.util.Arrays;

public class GradeBook {
    public static void main(String[] args) {
        int[][] grades = {
            {8, 7, 9, 6},
            {5, 9, 7, 8},
            {10, 6, 8, 9}
        };
        System.out.println("rows " + grades.length + ", columns " + grades[0].length);
        // Hai lần nhảy: grades[1] chọn hàng 1, rồi [2] chọn ô số 2 của hàng đó
        System.out.println("grades[1][2] = " + grades[1][2]);
        for (int r = 0; r < grades.length; r++) {
            // Đặt lại tổng cho mỗi học viên, nếu để ngoài vòng ngoài thì các hàng cộng dồn
            int sum = 0;
            // Dùng độ dài của chính hàng r, không viết cứng số 4
            for (int c = 0; c < grades[r].length; c++) {
                sum += grades[r][c];
            }
            System.out.println("student " + r + " total " + sum);
        }
        // deepToString đi vào cả hai tầng, còn println(grades) chỉ ra địa chỉ hàng
        System.out.println(Arrays.deepToString(grades));
    }
}
