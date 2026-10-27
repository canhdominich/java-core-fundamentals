public class Q3 {
    public static void main(String[] args) {
        Card u = new Card();
        Card v = new Card();
        u.points = 3;
        v = u;
        v.points = 9;
        Card w = v;
        w.points = w.points + 1;
        System.out.println(u.points + " " + v.points + " " + w.points);
    }
}

class Card {
    int points;
}
