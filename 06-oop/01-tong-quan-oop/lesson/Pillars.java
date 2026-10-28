public class Pillars {
    public static void main(String[] args) {
        Card[] cards = { new BasicCard("An"), new GoldCard("Binh") };
        for (Card card : cards) {
            card.earn(10);
            System.out.println(card.describe());
        }
    }
}

// abstract: khuôn khung, không dập thẻ trực tiếp từ nó được
abstract class Card {
    private final String owner;
    private int points;

    Card(String owner) { this.owner = owner; }

    abstract int multiplier();

    void earn(int amount) { points = points + amount * multiplier(); }

    String describe() { return owner + " has " + points + " points"; }
}

// extends: lấy lại toàn bộ Card, chỉ điền chỗ trống
class BasicCard extends Card {
    BasicCard(String owner) { super(owner); }
    int multiplier() { return 1; }
}

class GoldCard extends Card {
    GoldCard(String owner) { super(owner); }
    int multiplier() { return 2; }
}
