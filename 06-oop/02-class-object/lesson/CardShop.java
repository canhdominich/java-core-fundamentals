public class CardShop {
    public static void main(String[] args) {
        Card a = new Card();
        System.out.println("step 1: " + a.owner + " " + a.points);
        a.owner = "An";
        a.points = 30;
        System.out.println("step 2: " + a.describe());
        // Chép mũi tên, không tạo thẻ mới
        Card b = a;
        b.points = 50;
        System.out.println("step 3: " + a.points + " " + (a == b));
        Card c = new Card();
        c.owner = "An";
        c.points = 50;
        System.out.println("step 4: " + (a == c));
        // Mảng mới chỉ có các ô null, chưa có thẻ nào
        Card[] cards = new Card[2];
        System.out.println("step 5: " + cards[0]);
        cards[0] = a;
        cards[1] = c;
        System.out.println("step 5: " + cards[1].points);
    }
}

class Card {
    String owner;
    int points;

    String describe() {
        return owner + " has " + points;
    }
}
