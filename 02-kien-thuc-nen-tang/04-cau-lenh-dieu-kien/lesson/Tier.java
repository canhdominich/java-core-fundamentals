public class Tier {
    public static void main(String[] args) {
        int points = 79;
        String tier;
        if (points >= 80) { // điều kiện chặt nhất đứng đầu
            tier = "GOLD";
        } else if (points >= 65) { // chỉ được thử khi điều kiện trên sai
            tier = "SILVER";
        } else if (points >= 50) {
            tier = "BRONZE";
        } else { // nhận mọi trường hợp còn lại
            tier = "NONE";
        }
        System.out.println(points + " points: " + tier);
    }
}
