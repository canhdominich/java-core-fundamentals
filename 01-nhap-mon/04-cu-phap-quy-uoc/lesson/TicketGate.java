public class TicketGate {
    // Trả về nhãn vé theo tuổi, mặc định là trẻ em
    static String label(int age) {
        String result = "child";
        // Sau điều kiện không có dấu chấm phẩy: khối ngay bên dưới mới thuộc về if
        if (age >= 18) {
            result = "adult";
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("age 10: " + label(10));
        System.out.println("age 30: " + label(30));
    }
}
