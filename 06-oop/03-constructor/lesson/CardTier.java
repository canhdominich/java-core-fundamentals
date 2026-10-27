public class CardTier {
    public static void main(String[] args) {
        Card a = new Card("An", 30, "gold");
        Card b = new Card("Binh", 5);
        Card c = new Card("Chi");
        System.out.println(a.describe());
        System.out.println(b.describe());
        System.out.println(c.describe());
        try {
            new Card("Dung", 1, " ");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

class Card {
    String owner;
    int points;
    String tier;

    Card(String owner, int points, String tier) {
        if (owner == null || owner.isBlank()) {
            throw new IllegalArgumentException("owner must not be blank");
        }
        if (tier == null || tier.isBlank()) {
            throw new IllegalArgumentException("tier must not be blank");
        }
        this.owner = owner;
        this.points = points;
        this.tier = tier;
    }

    Card(String owner, int points) {
        this(owner, points, "bronze");
    }

    Card(String owner) {
        this(owner, 0);
    }

    String describe() {
        return owner + " has " + points + " points, tier " + tier;
    }
}
