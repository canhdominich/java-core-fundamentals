public class Shop {
    public static void main(String[] args) {
        Invoice a = new Invoice();
        Invoice b = new Invoice();
        System.out.println(a.number());
        System.out.println(b.number());
        // Gọi qua tên lớp, không cần đối tượng nào
        System.out.println(Invoice.issued());
    }
}

class Invoice {
    // Hằng số: một bản chung, gán một lần
    private static final int FIRST_NUMBER = 1000;
    // Một bản chung cho cả lớp, đổi được
    private static int nextNumber = FIRST_NUMBER;
    // Mỗi hoá đơn một bản riêng, gán đúng một lần
    private final int number;

    Invoice() {
        // Lấy số hiện tại rồi tăng bộ đếm chung lên một
        this.number = nextNumber++;
    }

    int number() {
        return number;
    }

    static int issued() {
        return nextNumber - FIRST_NUMBER;
    }
}
