public class Countdown {
    static int sum(int n) {
        // Điều kiện dừng: không có nó thì khung chồng mãi
        if (n == 0) {
            return 0;
        }
        // Mỗi lời gọi này đặt thêm một khung mới lên stack
        return n + sum(n - 1);
    }

    public static void main(String[] args) {
        System.out.println(sum(3));
    }
}
