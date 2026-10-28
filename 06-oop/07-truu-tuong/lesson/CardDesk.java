public class CardDesk {
    public static void main(String[] args) {
        // Biến có kiểu trừu tượng Card nhưng giữ một thẻ Vàng đã hoàn chỉnh
        Card card = new GoldCard("Binh");
        card.earn(5);
        System.out.println(card.describe());
    }
}

abstract class Card {
    private final String owner;
    private int points;

    Card(String owner) { this.owner = owner; }

    // Hai chỗ trống: mỗi loại thẻ tự điền
    abstract int multiplier();

    abstract String tier();

    // Khung dựng sẵn: final nên lớp con không đổi được thứ tự các bước
    final boolean earn(int amount) {
        if (amount <= 0) { return false; }
        points = points + amount * multiplier();
        return true;
    }

    final String describe() { return owner + " (" + tier() + ") has " + points + " points"; }
}

class GoldCard extends Card {
    GoldCard(String owner) { super(owner); }
    @Override int multiplier() { return 2; }
    @Override String tier() { return "gold"; }
}
