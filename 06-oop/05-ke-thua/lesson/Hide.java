public class Hide {
    public static void main(String[] args) {
        Card card = new GoldCard();
        System.out.println(card.label + " " + card.tier() + " " + ((GoldCard) card).label);
    }
}

class Card {
    String label = "card";

    String tier() {
        return "basic";
    }
}

class GoldCard extends Card {
    String label = "gold";

    @Override
    String tier() {
        return "gold";
    }
}
