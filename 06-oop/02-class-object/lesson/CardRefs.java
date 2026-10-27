public class CardRefs {
    public static void main(String[] args) {
        // Field chưa gán nhận giá trị mặc định: null và 0
        Card a = new Card();
        System.out.println(a.owner + " " + a.points);
        a.owner = "An";
        a.points = 30;
        // Không có new: chỉ chép mũi tên, a và b cùng một thẻ
        Card b = a;
        b.points = 50;
        System.out.println(a.describe());
        // Thẻ thứ hai, giá trị giống nhưng là object khác
        Card c = new Card();
        c.owner = "An";
        c.points = 50;
        // == so hai mũi tên, không so giá trị bên trong
        System.out.println(a == b);
        System.out.println(a == c);
    }
}

class Card {
    String owner;
    int points;

    String describe() {
        return owner + " has " + points;
    }
}
