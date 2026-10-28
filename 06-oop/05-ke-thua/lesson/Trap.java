public class Trap {
    public static void main(String[] args) {
        new GoldCard();
    }
}

class Card {
    Card() {
        System.out.println("constructor sees: " + tier());
    }

    String tier() {
        return "basic";
    }
}

class GoldCard extends Card {
    private String label = "gold";

    @Override
    String tier() {
        return label;
    }
}
