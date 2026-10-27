public class CardBirth {
    public static void main(String[] args) {
        Card a = new Card("An", 30);
        Card b = new Card("Binh");
        System.out.println(a.describe());
        System.out.println(b.describe());
    }
}

class Card {
    String owner;
    int points;

    // Constructor đầy đủ: nơi duy nhất kiểm tra và gán dữ liệu
    Card(String owner, int points) {
        if (owner == null || owner.isBlank()) {
            throw new IllegalArgumentException("owner must not be blank");
        }
        // this.owner là field, owner đứng một mình là tham số
        this.owner = owner;
        this.points = points;
    }

    Card(String owner) {
        // Phải là dòng đầu tiên, chuyển sang constructor đầy đủ
        this(owner, 0);
    }

    String describe() {
        return owner + " has " + points + " points";
    }
}
