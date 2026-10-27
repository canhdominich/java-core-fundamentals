public class ZoneFee {
    public static void main(String[] args) {
        int zone = 4;
        int fee = switch (zone) {
            case 1 -> 20_000;
            case 2, 3 -> 30_000; // gộp nhiều nhãn bằng dấu phẩy
            default -> {
                int base = 35_000;
                yield base + 5_000; // khối nhiều lệnh dùng yield để trả giá trị
            }
        };
        System.out.println("zone " + zone + " fee " + fee);
    }
}
