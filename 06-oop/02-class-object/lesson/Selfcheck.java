public class Selfcheck {
    public static void main(String[] args) {
        Card x = new Card();
        Card y = x;
        Card z = new Card();
        x.points = 4;
        y.points = y.points * 2;
        z.points = x.points;
        z.points = z.points + 1;
        y = z;
        y.points = y.points + 10;
        System.out.println(x.points + " " + y.points + " " + z.points);
        System.out.println((x == y) + " " + (y == z));
    }
}

class Card {
    int points;
}
