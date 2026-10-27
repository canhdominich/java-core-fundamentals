public class ShipCheck {
    static String check(String friend, int fileRelease, int friendJava) {
        // Phần đầu dòng dùng chung cho cả hai nhánh
        String head = friend + ": release " + fileRelease + " on Java " + friendJava + ": ";
        // File chạy được khi release của file không lớn hơn Java của người nhận
        if (fileRelease <= friendJava) {
            return head + "runs";
        }
        return head + "refused, rebuild with --release " + friendJava;
    }

    public static void main(String[] args) {
        System.out.println(check("Linh", 17, 17));
        System.out.println(check("Hoa", 17, 11));
        System.out.println(check("Nam", 17, 8));
    }
}
