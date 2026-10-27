public class CartSum {
    public static void main(String[] args) {
        int[] prices = {45_000, 18_500, 32_000};
        int total = 0;
        for (int i = 0; i < prices.length; i++) { // khởi tạo một lần, kiểm tra trước mỗi vòng, cập nhật sau mỗi vòng
            total += prices[i];
            System.out.println("i=" + i + " total=" + total);
        }
        System.out.println("sum = " + total);
    }
}
