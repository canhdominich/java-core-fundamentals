public class SetterTrap {
    public static void main(String[] args) {
        Card card = new Card();
        card.setPoints(30);
        card.setPoints(-500);
        System.out.println(card.getPoints());
    }
}

class Card {
    private int points;

    // Setter chỉ gán, không có luật nào nên chẳng khác field public
    void setPoints(int points) {
        this.points = points;
    }

    int getPoints() {
        return points;
    }
}
