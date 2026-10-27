public class Practice {
    public static void main(String[] args) {
        // Dữ liệu lấy từ tham số dòng lệnh, không có thì dùng latte,2
        String line = args.length > 0 ? args[0] : "latte,2";
        System.out.println("step 1: " + total(line));
    }

    static int total(String line) {
        // Chia 300 cho số lượng, số lượng 0 sẽ gây lỗi
        return 300 / quantity(line);
    }

    static int quantity(String line) {
        String[] parts = line.split(",");
        return Integer.parseInt(parts[1]);
    }
}
