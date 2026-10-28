import java.util.List;

public class Members {
    public static void main(String[] args) {
        Card a = new Card("An");
        Card b = new Card("An");
        System.out.println(a.equals(b));
        // contains gọi equals(Object), nên không thấy bản nhận Card
        System.out.println(List.of(a).contains(b));
    }
}

class Card {
    private final String owner;

    Card(String owner) {
        this.owner = owner;
    }

    // Đây là nạp chồng, không phải ghi đè equals(Object)
    public boolean equals(Card other) {
        return owner.equals(other.owner);
    }
}
