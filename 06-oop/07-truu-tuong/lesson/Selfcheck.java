public class Selfcheck {
    public static void main(String[] args) {
        // Bài 1
        GoldCard gold = new GoldCard("Binh");
        gold.earn(7);
        System.out.println(gold.describe());
        // Tự kiểm tra câu 2
        Card c = new PartnerCard("Chi");
        c.earn(4);
        System.out.println(c.describe());
        // Bài 3 sau khi sửa
        Card p = new PartnerCard("Chi");
        p.earn(5);
        System.out.println(p.describe());
        // Tự kiểm tra câu 3
        NoisyCard n = new NoisyCard("Z");
        n.earn(2);
        System.out.println(n.describe());
        // Tự kiểm tra câu 4 sau khi sửa
        System.out.println(new GoldCard("An").earn(0) + " " + new GoldCard("An").earn(3));
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

class NoisyCard extends Card {
    NoisyCard(String owner) { super(owner); }

    @Override
    int multiplier() {
        System.out.print("M ");
        return 1;
    }

    @Override
    String tier() {
        System.out.print("T ");
        return "noisy";
    }
}
