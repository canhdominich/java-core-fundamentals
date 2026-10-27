public class Shipping {
    public static void main(String[] args) {
        int zone = 9;
        double weight = 6.0;
        boolean member = true;
        if (weight <= 0) { // chặn đầu vào sai trước
            System.out.println("weight must be positive");
            return;
        }
        int fee = switch (zone) {
            case 1 -> 20_000;
            case 2, 3 -> 30_000;
            default -> 35_000;
        };
        if (weight > 2) {
            fee += (int) Math.ceil(weight - 2) * 5_000; // mỗi kg đã bắt đầu vượt 2 kg tính 5000
        }
        if (member && fee > 40_000) {
            fee -= 10_000; // thành viên giảm 10000 khi phí vượt 40000
        }
        System.out.println("fee = " + fee);
    }
}
