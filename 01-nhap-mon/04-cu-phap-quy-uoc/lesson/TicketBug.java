public class TicketBug {
    static String label(int age) {
        String result = "child";
        // Dấu chấm phẩy ngay sau điều kiện là một câu lệnh rỗng, nên if kết thúc ở đó
        if (age >= 18); {
            // Khối này không còn thuộc về if, nó luôn chạy
            result = "adult";
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("age 10: " + label(10));
        System.out.println("age 30: " + label(30));
    }
}
