public class Platinum {
    public static void main(String[] args) {
        PlatinumCard chi = new PlatinumCard("Chi");
        chi.earn(10);
        System.out.println(chi.describe());
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

class PlatinumCard extends GoldCard {
    PlatinumCard(String owner) {
        super(owner);
    }

    @Override
    int multiplier() {
        // super.multiplier() chạy bản của GoldCard, không lặp lại số 2
        return super.multiplier() * 2;
    }

    @Override
    String tier() {
        return "platinum";
    }
}
