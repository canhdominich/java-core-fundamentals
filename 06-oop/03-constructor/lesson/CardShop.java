public class CardShop {
    public static void main(String[] args) {
        Card a = new Card("An", 30);
        System.out.println("step 1: " + a.describe());
        try {
            new Card(" ", 5);
        } catch (IllegalArgumentException e) {
            System.out.println("step 2: " + e.getMessage());
        }
        Card b = new Card("Binh");
        System.out.println("step 3: " + b.describe());
    }
}

class Card {
    String owner;
    int points;

    // Constructor đầy đủ
    Card(String owner, int points) {
        if (owner == null || owner.isBlank()) {
            throw new IllegalArgumentException("owner must not be blank");
        }
        this.owner = owner;
        this.points = points;
    }

    Card(String owner) {
        // Điểm khởi đầu là 0, dùng lại constructor đầy đủ
        this(owner, 0);
    }

    String describe() {
        return owner + " has " + points;
    }
}
