public class CardCopy {
    public static void main(String[] args) {
        Card a = new Card("An", 30);
        Card copy = new Card(a);
        copy.points = 99;
        System.out.println(a.describe());
        System.out.println(copy.describe());
        System.out.println(a == copy);
        try {
            new Card("  ", 5);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        try {
            new Card("An", -1);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

class Card {
    String owner;
    int points;

    Card(String owner, int points) {
        if (owner == null || owner.isBlank()) {
            throw new IllegalArgumentException("owner must not be blank");
        }
        if (points < 0) {
            throw new IllegalArgumentException("points must not be negative: " + points);
        }
        this.owner = owner;
        this.points = points;
    }

    Card(Card other) {
        // Lấy giá trị của thẻ cũ để dập thẻ mới thật sự
        this(other.owner, other.points);
    }

    String describe() {
        return owner + " has " + points + " points";
    }
}
