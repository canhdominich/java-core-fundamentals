public class Order {
    public static void main(String[] args) {
        new PlatinumCard();
    }
}

class Card {
    Card() {
        System.out.println("Card");
    }
}

class GoldCard extends Card {
    GoldCard() {
        System.out.println("GoldCard");
    }
}

class PlatinumCard extends GoldCard {
    PlatinumCard() {
        System.out.println("PlatinumCard");
    }
}
