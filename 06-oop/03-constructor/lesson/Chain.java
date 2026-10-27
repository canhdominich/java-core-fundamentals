public class Chain {
    public static void main(String[] args) {
        Card c = new Card();
        System.out.println(c.owner + " " + c.points);
    }
}

class Card {
    String owner;
    int points;

    Card(String owner, int points) {
        this.owner = owner;
        this.points = points;
        System.out.println("two-arg " + owner + " " + points);
    }

    Card(String owner) {
        this(owner, 10);
        System.out.println("one-arg " + owner);
    }

    Card() {
        this("guest");
        System.out.println("no-arg");
    }
}
