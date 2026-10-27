public class Trace {
    public static void main(String[] args) {
        Card p = new Card();
        p.points = 10;
        Card q = p;
        q.points = q.points + 5;
        Card r = new Card();
        r.points = p.points;
        r.points = r.points + 1;
        q = r;
        q.points = 0;
        System.out.println(p.points + " " + q.points + " " + r.points);
    }
}

class Card {
    int points;
}
