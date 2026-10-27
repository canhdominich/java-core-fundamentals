public class InitOrder {
    public static void main(String[] args) {
        Card c = new Card("An");
        System.out.println(c.points);
    }
}

class Card {
    String owner;
    int points = 5;

    Card(String owner) {
        System.out.println("entering: " + this.owner + " " + points);
        this.owner = owner;
        points = points + 1;
    }
}
