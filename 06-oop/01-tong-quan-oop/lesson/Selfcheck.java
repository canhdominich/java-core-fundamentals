public class Selfcheck {
    public static void main(String[] args) {
        Card p = new Card("P");
        Card q = new Card("Q");
        p.earn(10);
        q.earn(p.current());
        p.earn(q.current());
        System.out.println(p.current() + " " + q.current());
    }
}

class Card {
    private int points;

    void earn(int amount) {
        points = points + amount;
    }

    int current() {
        return points;
    }

    Card(String name) {
    }
}
