public class WrongName {
    public static void main(String[] args) {
        Card card = new PartnerCard("Chi");
        card.earn(5);
        System.out.println(card.getPoints());
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

    void earn(int amount) {
        points = points + amount * multiplier();
    }

    int getPoints() {
        return points;
    }
}

class PartnerCard extends Card {
    PartnerCard(String owner) {
        super(owner);
    }

    int multipler() {
        return 3;
    }
}
