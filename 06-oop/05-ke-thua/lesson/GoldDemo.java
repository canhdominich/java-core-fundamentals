public class GoldDemo {
    public static void main(String[] args) {
        GoldCard binh = new GoldCard("Binh");
        Card same = binh;
        binh.earn(10);
        System.out.println(binh.describe());
        System.out.println(same.describe());
        Card an = new Card("An");
        an.earn(10);
        System.out.println(an.describe());
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
        // multiplier() ở đây chạy bản của object thật, không phải của Card
        points = points + amount * multiplier();
        return true;
    }

    String describe() {
        return owner + " (" + tier() + ") has " + points + " points";
    }
}

class GoldCard extends Card {
    GoldCard(String owner) {
        // Nhờ Card dựng phần của nó trước, bắt buộc là dòng đầu
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
