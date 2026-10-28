public class CardPerk {
    public static void main(String[] args) {
        System.out.println(new BasicCard("An").summary());
        System.out.println(new GoldCard("Binh").summary());
    }
}

abstract class Card {
    private final String owner;
    private int points;

    Card(String owner) { this.owner = owner; }

    abstract int multiplier();

    abstract String tier();

    // Điểm mở rộng tuỳ chọn: có thân mặc định, lớp con muốn thì ghi đè
    String perk() { return "none"; }

    final String summary() {
        return owner + " (" + tier() + ") has " + points + " points, perk " + perk();
    }
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
    @Override String perk() { return "free upsize"; }
}
