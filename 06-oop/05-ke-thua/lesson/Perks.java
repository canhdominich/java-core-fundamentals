import java.util.List;

public class Perks {
    public static void main(String[] args) {
        List<Card> cards = List.of(new Card("An"), new GoldCard("Binh"), new Card("Chi"));
        for (Card card : cards) {
            card.earn(10);
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

    int freeDrinks() {
        return 0;
    }

    boolean earn(int amount) {
        if (amount <= 0) {
            return false;
        }
        points = points + amount * multiplier();
        return true;
    }

    String describe() {
        // Sửa một chỗ này, mọi lớp con đổi theo
        return owner + " (" + tier() + ") has " + points + " points, " + freeDrinks() + " free drinks";
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

    // Chỉ GoldCard thay số ly miễn phí mặc định
    @Override
    int freeDrinks() {
        return 2;
    }
}
