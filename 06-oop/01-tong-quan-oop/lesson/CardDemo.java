public class CardDemo {
    public static void main(String[] args) {
        Card an = new Card("An");
        Card binh = new Card("Binh");
        an.earn(30);
        binh.earn(5);
        an.earn(20);
        System.out.println(an.describe());
        System.out.println(binh.describe());
    }
}

class Card {
    private String owner;
    private int points;

    Card(String name) {
        owner = name;
    }

    void earn(int amount) {
        // Cộng vào points của chính thẻ đang được gọi
        points = points + amount;
    }

    String describe() {
        return owner + " has " + points + " points";
    }
}
