public class Trace {
    public static void main(String[] args) {
        Card c = new Card();
        System.out.println(c.earn(50) + " " + c.getPoints());
        System.out.println(c.redeem(80) + " " + c.getPoints());
        System.out.println(c.earn(0) + " " + c.getPoints());
        System.out.println(c.redeem(50) + " " + c.getPoints());
        System.out.println(c.earn(25) + " " + c.getPoints());
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
