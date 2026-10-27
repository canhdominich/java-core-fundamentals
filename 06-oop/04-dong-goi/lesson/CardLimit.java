public class CardLimit {
    public static void main(String[] args) {
        Card card = new Card();
        System.out.println(card.earn(900) + " " + card.getPoints());
        System.out.println(card.earn(200) + " " + card.getPoints());
        System.out.println(card.earn(100) + " " + card.getPoints());
        System.out.println(card.isVip());
        System.out.println(card.redeem(1001) + " " + card.getPoints());
    }
}

class Card {
    private int points;

    boolean earn(int amount) {
        if (amount <= 0 || points + amount > 1000) {
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

    boolean isVip() {
        return points >= 100;
    }

    int getPoints() {
        return points;
    }
}
