public class Q2 {
    public static void main(String[] args) {
        Card c = new Card("An", 30);
        System.out.println(c.owner + " " + c.points);
    }
}

class Card {
    String owner;
    int points;

    Card(String owner, int points) {
        this.owner = owner;
        points = points;
    }
}
