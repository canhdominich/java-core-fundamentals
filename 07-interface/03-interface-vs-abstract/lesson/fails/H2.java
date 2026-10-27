public class H2 {
    public static void main(String[] args) {
        System.out.println("x");
    }
}

class Billing {
}

class Audit {
}

class CardPay extends Billing, Audit {
}
