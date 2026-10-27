public class CardDemo {
    public static void main(String[] args) {
        // Mỗi lệnh new dập ra một thẻ riêng, có bộ field riêng
        Card an = new Card("An");
        Card binh = new Card("Binh");
        // Chỉ thẻ an đổi, thẻ binh đứng yên
        an.earn(30);
        binh.earn(5);
        an.earn(20);
        System.out.println(an.describe());
        System.out.println(binh.describe());
    }
}

class Card {
    // private: chỉ code trong class Card đụng được, bài 24 sẽ giải thích
    private String owner;
    private int points;

    // Constructor chạy ngay khi có new, bài 23 sẽ giải thích
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
