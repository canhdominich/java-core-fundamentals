public class TierDesk {
    public static void main(String[] args) {
        boolean member = true;
        int discountPercent;
        if (member) {
            discountPercent = 10;
        } else {
            discountPercent = 0;
        }
        System.out.println("step 1: " + discountPercent);
        int points = 79;
        String tier;
        if (points >= 80) {
            tier = "GOLD";
        } else if (points >= 65) { // thứ tự từ chặt đến lỏng
            tier = "SILVER";
        } else if (points >= 50) {
            tier = "BRONZE";
        } else {
            tier = "NONE";
        }
        System.out.println("step 2: " + tier);
        int vip = 95;
        String vipTier;
        if (vip >= 80) {
            vipTier = "GOLD";
        } else if (vip >= 65) {
            vipTier = "SILVER";
        } else if (vip >= 50) {
            vipTier = "BRONZE";
        } else {
            vipTier = "NONE";
        }
        System.out.println("step 3: " + vipTier);
        int zone = 2;
        int fee = 0;
        switch (zone) {
            case 1:
                fee = 20_000;
                break; // break chặn chạy xuyên
            case 2:
                fee = 30_000;
                break;
            default:
                fee = 35_000;
        }
        System.out.println("step 4: " + fee);
        int other = 9;
        int fee2 = switch (other) {
            case 1 -> 20_000;
            case 2 -> 30_000;
            default -> 35_000;
        };
        System.out.println("step 5: " + fee2);
    }
}
