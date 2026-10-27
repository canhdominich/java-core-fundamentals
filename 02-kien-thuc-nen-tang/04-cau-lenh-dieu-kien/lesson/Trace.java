public class Trace {
    static String tier(int points) {
        String tier = "NONE";
        if (points >= 80) {
            tier = "GOLD";
        } else if (points >= 65) {
            tier = "SILVER";
        } else if (points >= 50) {
            tier = "BRONZE";
        }
        return tier;
    }

    public static void main(String[] args) {
        System.out.println("80 " + tier(80));
        System.out.println("65 " + tier(65));
        System.out.println("49 " + tier(49));
    }
}
