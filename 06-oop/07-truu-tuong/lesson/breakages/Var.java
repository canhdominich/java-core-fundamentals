abstract class Card {
    private final String owner;
    private int points;

    Card(String owner) { this.owner = owner; }

    abstract int multiplier();

    abstract String tier();

    final boolean earn(int amount) {
        if (amount <= 0) { return false; }
        points = points + amount * multiplier();
        return true;
    }

    final String describe() { return owner + " (" + tier() + ") has " + points + " points"; }
}

class GoldCard extends Card {
    GoldCard(String owner) { super(owner); }
    @Override int multiplier() { return 2; }
    @Override String tier() { return "gold"; }
}

class Use {
    void run() {
        Card a = new GoldCard("An");
        GoldCard b = new Card("An");
    }
}
