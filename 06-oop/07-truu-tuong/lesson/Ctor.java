public class Ctor {
    public static void main(String[] args) {
        new GoldCard("Binh");
    }
}

abstract class Card {
    Card(String owner) {
        System.out.println("Card constructor");
    }

    abstract int multiplier();
}

class GoldCard extends Card {
    GoldCard(String owner) {
        super(owner);
        System.out.println("GoldCard constructor");
    }

    @Override
    int multiplier() { return 2; }
}
