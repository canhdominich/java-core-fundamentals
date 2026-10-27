public class PriceCalc {
    public static void main(String[] args) {
        int price = 1_500_000;
        int discount = price * 10 / 100; // nhân trước, chia sau
        System.out.println("step 1: " + discount);
        int finalPrice = price - discount;
        System.out.println("step 2: " + finalPrice);
        int each = finalPrice / 7; // phần mỗi người trả
        int left = finalPrice % 7; // số đồng dư không chia hết
        System.out.println("step 3: " + each + " " + left);
        int tickets = 0;
        int first = tickets++; // lấy 0 rồi mới tăng
        int second = ++tickets; // tăng lên 2 rồi mới lấy
        System.out.println("step 4: " + first + " " + second + " " + tickets);
        String card = null;
        boolean vip = card != null && card.startsWith("VIP"); // kiểm tra null ở vế trái
        System.out.println("step 5: " + vip);
    }
}
