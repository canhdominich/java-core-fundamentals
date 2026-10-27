public class Short {
    public static void main(String[] args) {
        System.out.println(quantity("tea"));
    }

    static int quantity(String line) {
        String[] parts = line.split(",");
        // Chuỗi tea không có dấu phẩy nên parts chỉ có một phần tử
        return Integer.parseInt(parts[1]);
    }
}
