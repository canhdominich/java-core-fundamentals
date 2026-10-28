public class CardDeskPlus {
    public static void main(String[] args) {
        Card[] cards = { new BasicCard("An"), new GoldCard("Binh"), new PartnerCard("Chi") };
        for (Card card : cards) {
            Checkout.pay(card, 50000);
            System.out.println(card.describe());
        }
    }
}

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

class BasicCard extends Card {
    BasicCard(String owner) { super(owner); }
    @Override int multiplier() { return 1; }
    @Override String tier() { return "basic"; }
}

class GoldCard extends Card {
    GoldCard(String owner) { super(owner); }
    @Override int multiplier() { return 2; }
    @Override String tier() { return "gold"; }
}

class PartnerCard extends Card {
    PartnerCard(String owner) { super(owner); }
    @Override int multiplier() { return 3; }
    @Override String tier() { return "partner"; }
}

class Checkout {
    // Không nhắc tới loại thẻ nào, vì earn và multiplier lo phần riêng
    static void pay(Card card, int bill) {
        card.earn(bill / 10000);
    }
}
