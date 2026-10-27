public class Compare {
    public static void main(String[] args) {
        // Giả lập chuỗi do người dùng gõ vào: một đối tượng String mới
        String typed = new String("yes");
        // == so sánh địa chỉ, equals so sánh nội dung
        System.out.println("== " + (typed == "yes"));
        System.out.println("equals " + typed.equals("yes"));
        String missing = null;
        // Đặt hằng ở bên trái thì null không gây lỗi
        System.out.println("null safe " + "yes".equals(missing));
    }
}
