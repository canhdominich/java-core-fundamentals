public class Staff {
    public static void main(String[] args) {
        Card card = new StaffCard("Dung");
        Checkout.pay(card, 30000);
        System.out.println(card.describe());
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

class StaffCard extends Card {
    StaffCard(String owner) { super(owner); }

    @Override
    int multiplier() { return 4; }

    @Override
    String tier() { return "staff"; }
}

class Checkout {
    static void pay(Card card, int bill) {
        card.earn(bill / 10000);
    }
}
