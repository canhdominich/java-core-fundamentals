public class Trace {
    public static void main(String[] args) {
        Card a = new GoldCard("An");
        Card b = new Card("Binh");
        a.earn(10);
        b.earn(10);
        System.out.println(a.getPoints());
        System.out.println(b.getPoints());
        // Từ dòng này a trỏ sang object của b
        a = b;
        a.earn(5);
        System.out.println(b.getPoints());
    }
}

class Card {
    private final String owner;
    private int points;

    Card(String owner) {
        this.owner = owner;
    }

    int multiplier() {
        return 1;
    }

    String tier() {
        return "basic";
    }

    boolean earn(int amount) {
        if (amount <= 0) {
            return false;
        }
        points = points + amount * multiplier();
        return true;
    }

    int getPoints() {
        return points;
    }

    String describe() {
        return owner + " (" + tier() + ") has " + points + " points";
    }
}

class GoldCard extends Card {
    GoldCard(String owner) {
        super(owner);
    }

    @Override
    int multiplier() {
        return 2;
    }

    @Override
    String tier() {
        return "gold";
    }
}
