public class WrongTier {
    public static void main(String[] args) {
        int points = 95;
        String tier;
        if (points >= 50) { // điều kiện lỏng nhất đứng đầu nên chặn hết các nhánh sau
            tier = "BRONZE";
        } else if (points >= 65) {
            tier = "SILVER";
        } else if (points >= 80) {
            tier = "GOLD";
        } else {
            tier = "NONE";
        }
        System.out.println(points + " points: " + tier);
    }
}
