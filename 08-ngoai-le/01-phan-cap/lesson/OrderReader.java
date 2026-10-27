public class OrderReader {
    public static void main(String[] args) {
        System.out.println("start");
        System.out.println(total("latte,2"));
        // Dòng này làm ném ngoại lệ vì "x" không phải số
        System.out.println(total("mocha,x"));
        // Chương trình không bao giờ chạy tới dòng này
        System.out.println("end");
    }

    static int total(String line) {
        return quantity(line) * 30;
    }

    static int quantity(String line) {
        String[] parts = line.split(",");
        // parseInt ném NumberFormatException khi chuỗi không phải số
        return Integer.parseInt(parts[1]);
    }
}
