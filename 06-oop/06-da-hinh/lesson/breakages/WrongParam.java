import java.util.List;

public class WrongParam {
    public static void main(String[] args) {
        List<Discount> rules = List.of(new Half());
        long price = 100000;
        for (Discount rule : rules) {
            price = rule.apply(price);
        }
        System.out.println(price);
    }
}

class Discount {
    long apply(long price) {
        return price;
    }
}

class Half extends Discount {
    long apply(int price) {
        return price / 2;
    }
}
