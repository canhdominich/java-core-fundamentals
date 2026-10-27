public class Swap {
    static void bonus(Card card) {
        card.points = card.points + 10;
    }

    static void replace(Card card) {
        card = new Card();
        card.points = 999;
    }

    public static void main(String[] args) {
        Card an = new Card();
        an.points = 5;
        bonus(an);
        System.out.println(an.points);
        replace(an);
        System.out.println(an.points);
    }
}

class Card {
    int points;
}
