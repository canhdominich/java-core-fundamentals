public class Breakages {
    static int forever(int n) {
        // Không có điều kiện dừng
        return forever(n + 1) + 1;
    }

    public static void main(String[] args) {
        try {
            forever(0);
        } catch (StackOverflowError e) {
            System.out.println("recursion: " + e.getClass().getSimpleName());
        }
        try {
            // Biến không giữ phiếu nào
            int[] trays = null;
            trays[0] = 1;
        } catch (NullPointerException e) {
            System.out.println("null array: " + e.getMessage());
        }
    }
}
