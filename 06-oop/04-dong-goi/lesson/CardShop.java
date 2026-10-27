public class CardShop {
    public static void main(String[] args) {
        Card card = new Card();
        card.earn(100);
        System.out.println("step 1: " + card.getPoints());
        System.out.println("step 2: " + card.earn(-5) + " " + card.getPoints());
        System.out.println("step 3: " + card.redeem(500) + " " + card.redeem(70) + " " + card.getPoints());
    }
}

class Card {
    private int points;

    boolean earn(int amount) {
        if (amount <= 0) {
            return false;
        }
        points = points + amount;
        return true;
    }

    boolean redeem(int amount) {
        if (amount <= 0 || amount > points) {
            return false;
        }
        points = points - amount;
        return true;
    }

    int getPoints() {
        return points;
    }
}
