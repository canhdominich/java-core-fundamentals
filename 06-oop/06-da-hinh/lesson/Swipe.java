import java.util.List;

public class Swipe {
    public static void main(String[] args) {
        // Danh sách kiểu cha chứa được mọi loại thẻ
        List<Card> cards = List.of(new Card("An"), new GoldCard("Binh"));
        for (Card card : cards) {
            Checkout.pay(card, 50000);
            System.out.println(card.describe());
        }
    }
}

class Checkout {
    static void pay(Card card, int bill) {
        // Cứ 10000 đồng được 1 điểm; Checkout không cần biết đây là thẻ loại nào
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
        // multiplier() chạy theo thẻ thật, dù earn chỉ viết một lần ở Card
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
