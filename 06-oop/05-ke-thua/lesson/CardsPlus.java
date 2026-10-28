import java.util.List;

public class CardsPlus {
    public static void main(String[] args) {
        StudentCard chi = new StudentCard("Chi");
        System.out.println(chi.describe());

        List<Card> cards = List.of(new Card("An"), new GoldCard("Binh"), chi);
        for (Card card : cards) {
            card.earn(10);
            System.out.println(card.describe());
        }
    }
}

class Card {
    private final String owner;
    private int points;

    Card(String owner) {
        this.owner = owner;
    }

    int multiplier() {
        return 1;
    }

    String tier() {
        return "basic";
    }

    boolean earn(int amount) {
        if (amount <= 0) {
            return false;
        }
        points = points + amount * multiplier();
        return true;
    }

    String describe() {
        return owner + " (" + tier() + ") has " + points + " points";
    }
}

class GoldCard extends Card {
    GoldCard(String owner) {
        super(owner);
    }

    @Override
    int multiplier() {
        return 2;
    }

    @Override
    String tier() {
        return "gold";
    }
}

class StudentCard extends Card {
    StudentCard(String owner) {
        super(owner);
    }

    @Override
    String tier() {
        return "student";
    }

    @Override
    String describe() {
        // Chạy describe() của Card rồi nối thêm phần của StudentCard
        return super.describe() + ", expires in 2027";
    }
}
