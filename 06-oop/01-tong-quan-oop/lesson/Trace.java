public class Trace {
    public static void main(String[] args) {
        Card x = new Card("X");
        Card y = new Card("Y");
        Card z = new Card("Z");
        x.earn(10);
        y.earn(20);
        x.earn(5);
        z.earn(1);
        System.out.println(x.describe());
        System.out.println(y.describe());
        System.out.println(z.describe());
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
