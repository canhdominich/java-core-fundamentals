public class Overload {
    static String desk(Card card) {
        return "general desk";
    }

    static String desk(GoldCard card) {
        return "gold desk";
    }

    public static void main(String[] args) {
        Card x = new GoldCard("Binh");
        GoldCard y = new GoldCard("Binh");
        System.out.println(desk(x));
        System.out.println(desk(y));
        System.out.println(desk((Card) y));
        System.out.println(x.describe());
    }
}

class Card {
    private final String owner;

    Card(String owner) {
        this.owner = owner;
    }

    String tier() {
        return "basic";
    }

    String describe() {
        return owner + " (" + tier() + ")";
    }
}

class GoldCard extends Card {
    GoldCard(String owner) {
        super(owner);
    }

    @Override
    String tier() {
        return "gold";
    }
}
