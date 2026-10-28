class Card {
    private final String owner;
    private int points;

    Card(String owner) {
        this.owner = owner;
    }

    String tier() {
        return "basic";
    }
}


class GoldCard extends Card {
    GoldCard(String owner) {
        super(owner);
    }

    String shout() {
        return owner + " gold";
    }
}
