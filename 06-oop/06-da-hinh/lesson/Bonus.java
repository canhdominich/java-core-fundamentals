public class Bonus {
    public static void main(String[] args) {
        Card a = new BonusCard("An");
        Checkout.pay(a, 50000);
        System.out.println(a.describe());
        Checkout.pay(a, 5000);
        System.out.println(a.describe());
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

    String describe() {
        return owner + " (" + tier() + ") has " + points + " points";
    }
}

class BonusCard extends Card {
    BonusCard(String owner) {
        super(owner);
    }

    @Override
    boolean earn(int amount) {
        // Không có điểm gốc thì không thưởng, rồi cộng 5 điểm thưởng mỗi lần
        if (amount <= 0) {
            return false;
        }
        return super.earn(amount + 5);
    }

    @Override
    String tier() {
        return "bonus";
    }
}
