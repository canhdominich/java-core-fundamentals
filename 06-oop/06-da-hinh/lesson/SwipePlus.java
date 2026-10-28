import java.util.List;

public class SwipePlus {
    static int total(List<Card> cards) {
        int sum = 0;
        for (Card card : cards) {
            sum = sum + card.getPoints();
        }
        return sum;
    }

    public static void main(String[] args) {
        List<Card> cards = List.of(new Card("An"), new GoldCard("Binh"), new PartnerCard("Chi"));
        for (Card card : cards) {
            Checkout.pay(card, 50000);
            System.out.println(card.describe());
        }
        System.out.println("total: " + total(cards));
    }
}

class Checkout {
    static void pay(Card card, int bill) {
        card.earn(bill / 10000);
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
