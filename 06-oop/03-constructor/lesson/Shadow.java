public class Shadow {
    public static void main(String[] args) {
        Card buggy = new Card("An", 30);
        System.out.println(buggy.owner + " " + buggy.points);
    }
}

class Card {
    String owner;
    int points;

    Card(String owner, int points) {
        // Tham số che khuất field nên mỗi dòng chép tham số vào chính nó
        owner = owner;
        points = points;
    }
}
