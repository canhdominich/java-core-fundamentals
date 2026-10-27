public class G1 {
    public static void main(String[] args) {
        System.out.println(new Member().badge());
    }
}

interface Loyalty {
    default String badge() { return "member"; }
}

interface Promo {
    default String badge() { return "promo"; }
}

class Member implements Loyalty, Promo {
}
