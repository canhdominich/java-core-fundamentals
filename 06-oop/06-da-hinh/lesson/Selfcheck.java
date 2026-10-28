import java.util.List;

public class Selfcheck {
    public static void main(String[] args) {
        Card c = new GoldCard("X");
        c.earn(3);
        System.out.println(c.describe());

        List<Card> cards = List.of(new Card("An"), new GoldCard("Binh"), new PartnerCard("Chi"));
        for (Card card : cards) {
            card.earn(2);
            card.earn(3);
        }
        for (Card card : cards) {
            System.out.println(card.describe());
        }

        Card typo = new TypoCard("Z");
        typo.earn(5);
        System.out.println(typo.describe());

        for (Card card : List.of(new Card("An"), new GoldCard("Binh"), new PartnerCard("Chi"))) {
            card.earn(3);
            System.out.println(card.describe());
        }
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

class PartnerCard extends Card {
    PartnerCard(String owner) {
        super(owner);
    }

    @Override
    int multiplier() {
        return 3;
    }

    @Override
    String tier() {
        return "partner";
    }
}

class TypoCard extends Card {
    TypoCard(String owner) {
        super(owner);
    }

    int multipler() {
        return 3;
    }
}
