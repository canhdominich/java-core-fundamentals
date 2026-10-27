public class CardVip {
    public static void main(String[] args) {
        Card an = new Card("An");
        an.earn(80);
        System.out.println(an.describe() + ", vip " + an.isVip());
        an.earn(25);
        System.out.println(an.describe() + ", vip " + an.isVip());
        System.out.println(an.redeem(40) + " " + an.describe());
        System.out.println(an.redeem(500) + " " + an.describe());
        an.reset();
        System.out.println(an.describe());
    }
}

class Card {
    private String owner;
    private int points;

    Card(String name) {
        owner = name;
    }

    void earn(int amount) {
        points = points + amount;
    }

    boolean isVip() {
        return points >= 100;
    }

    boolean redeem(int amount) {
        // Không đủ điểm thì từ chối, điểm giữ nguyên
        if (amount > points) {
            return false;
        }
        points = points - amount;
        return true;
    }

    void reset() {
        points = 0;
    }

    String describe() {
        return owner + " has " + points + " points";
    }
}
