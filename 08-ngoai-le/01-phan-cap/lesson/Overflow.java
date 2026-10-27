public class Overflow {
    public static void main(String[] args) {
        System.out.println(depth(1));
    }

    static int depth(int n) {
        // Đệ quy không có điểm dừng nên ngăn xếp tràn
        return depth(n + 1) + 1;
    }
}
