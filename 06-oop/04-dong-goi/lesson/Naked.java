public class Naked {
    public static void main(String[] args) {
        Card card = new Card();
        card.points = 30;
        card.points = card.points - 70;
        System.out.println(card.points);
    }
}

class Card {
    // Không có private nên dòng nào cũng ghi được
    int points;
}
