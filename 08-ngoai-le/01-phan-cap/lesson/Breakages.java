public class Breakages {
    // Trường chưa gán nên là null
    static String drink;

    public static void main(String[] args) {
        int zero = 0;
        int[] prices = new int[3];
        // try catch chỉ để in thông báo, bài sau mới học kỹ
        try {
            // Chia số nguyên cho 0 ném ArithmeticException
            System.out.println("1: " + 100 / zero);
        } catch (Exception e) {
            System.out.println("1: " + e);
        }
        try {
            // Gọi phương thức trên null ném NullPointerException
            System.out.println("2: " + drink.length());
        } catch (Exception e) {
            System.out.println("2: " + e);
        }
        try {
            // Mảng 3 phần tử chỉ có chỉ số 0, 1, 2
            System.out.println("3: " + prices[3]);
        } catch (Exception e) {
            System.out.println("3: " + e);
        }
        try {
            // Chuỗi không phải số ném NumberFormatException
            System.out.println("4: " + Integer.parseInt("2x"));
        } catch (Exception e) {
            System.out.println("4: " + e);
        }
    }
}
