public class CardArray {
    public static void main(String[] args) {
        Card[] cards = new Card[3];
        for (int i = 0; i < cards.length; i++) {
            // Mỗi ô phải có new riêng, nếu không vẫn là null
            cards[i] = new Card();
            cards[i].owner = "C" + i;
        }
        cards[1].points = 40;
        // best chỉ giữ mũi tên tới thẻ đang dẫn đầu
        Card best = cards[0];
        for (Card card : cards) {
            if (card.points > best.points) {
                best = card;
            }
        }
        best.points = best.points + 10;
        for (Card card : cards) {
            System.out.println(card.describe());
        }
    }
}

class Card {
    String owner;
    int points;

    String describe() {
        return owner + " has " + points;
    }
}
