import java.util.Arrays;

public class BeforeAfter {
    public static void main(String[] args) {
        // Trước: hai mảng song song, chỉ chỉ số nối tên với điểm
        String[] names = {"Binh", "An"};
        int[] points = {5, 30};
        Arrays.sort(names);
        System.out.println(names[0] + " has " + points[0] + " points");

        // Sau: tên và điểm nằm trong cùng một object nên đi đâu cũng đi cùng nhau
        Card[] cards = {new Card("Binh"), new Card("An")};
        cards[0].earn(5);
        cards[1].earn(30);
        Card first = cards[0];
        cards[0] = cards[1];
        cards[1] = first;
        System.out.println(cards[0].describe());
    }
}

class Card {
    private String owner;
    private int points;

    Card(String name) {
        owner = name;
    }

    void earn(int amount) {
        points = points + amount;
    }

    String describe() {
        return owner + " has " + points + " points";
    }
}
