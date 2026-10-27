public class Breakages {
    public static void main(String[] args) {
        Card d = null;
        try {
            System.out.println(d.points);
        } catch (NullPointerException e) {
            System.out.println("1: " + e.getMessage());
        }

        Card[] cards = new Card[3];
        System.out.println("2: " + cards[0]);
        try {
            cards[0].points = 5;
        } catch (NullPointerException e) {
            System.out.println("2: " + e.getMessage());
        }

        Card a = new Card();
        a.points = 50;
        Card c = new Card();
        c.points = 50;
        System.out.println("3: " + (a == c));
    }
}

class Card {
    String owner;
    int points;
}
