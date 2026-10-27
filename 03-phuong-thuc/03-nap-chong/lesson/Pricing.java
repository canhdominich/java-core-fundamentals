public class Pricing {
    static long price(int cups) {
        return cups * 45000L; // hậu tố L để phép nhân ra long
    }

    static long price(int cups, int percentOff) {
        return price(cups) * (100 - percentOff) / 100; // tái dùng phiên bản theo số ly
    }

    static long price(double kg) {
        return Math.round(kg * 200000); // làm tròn số thực thành long
    }

    static long price(String name) {
        if (name.equals("Latte")) {
            return 45000;
        }
        if (name.equals("Tea")) {
            return 30000;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("price(2)         = " + price(2));
        System.out.println("price(2, 10)     = " + price(2, 10));
        System.out.println("price(1.5)       = " + price(1.5));
        System.out.println("price(\"Latte\")   = " + price("Latte"));
        System.out.println("price(3L)        = " + price(3L)); // long mở rộng thành double
    }
}
