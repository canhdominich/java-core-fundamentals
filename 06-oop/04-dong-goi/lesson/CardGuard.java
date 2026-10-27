public class CardGuard {
    public static void main(String[] args) {
        Card card = new Card();
        System.out.println(card.earn(100));
        System.out.println(card.earn(-5));
        System.out.println(card.redeem(500));
        System.out.println(card.redeem(70));
        System.out.println(card.getPoints());
    }
}

class Card {
    // Khoá field: bên ngoài chỉ đi qua các method bên dưới
    private int points;

    boolean earn(int amount) {
        // Luật: chỉ nhận số dương, sai thì từ chối và không đổi points
        if (amount <= 0) {
            return false;
        }
        points = points + amount;
        return true;
    }

    boolean redeem(int amount) {
        // Luật: không đổi nhiều hơn số điểm đang có
        if (amount <= 0 || amount > points) {
            return false;
        }
        points = points - amount;
        return true;
    }

    // Chỉ đọc, không có setter nên không ai ghi đè trực tiếp
    int getPoints() {
        return points;
    }
}
