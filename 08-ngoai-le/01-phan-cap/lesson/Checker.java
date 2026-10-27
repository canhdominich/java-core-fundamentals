public class Checker {
    static String drinkName;

    public static void main(String[] args) {
        String mode = args[0];
        if (mode.equals("avg")) {
            int count = 0;
            // Trung bình giá khi chưa có đơn nào: chia cho 0
            System.out.println(300 / count);
        } else if (mode.equals("name")) {
            // Tên đồ uống chưa được gán nên là null
            System.out.println(drinkName.length());
        } else if (mode.equals("menu")) {
            String[] menu = {"latte", "mocha", "tea"};
            // Menu có 3 món, chỉ số hợp lệ là 0, 1, 2
            System.out.println(menu[3]);
        } else if (mode.equals("qty")) {
            // Số lượng "2x" không phải số
            System.out.println(Integer.parseInt("2x"));
        }
    }
}
