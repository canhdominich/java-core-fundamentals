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

    String badge() {
        return "gold";
    }
}

class Use {
    void run() {
        Card card = new GoldCard("Binh");
        card.badge();
    }
}
