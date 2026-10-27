public class Deep {
    public static void main(String[] args) {
        // Lớp ẩn danh: cài đặt interface ngay tại chỗ, không đặt tên
        PaymentMethod gift = new PaymentMethod() {
            public String name() {
                return "Gift";
            }

            public int fee(int price) {
                return 0;
            }
        };
        Both both = new Both();
        System.out.println(gift.name() + " " + PaymentMethod.MAX);
        System.out.println(both.name() + " " + both.print() + " " + (both instanceof Printable));
    }
}

// Hợp đồng của quầy thu ngân: chỉ khai báo phần đầu, chưa có thân
interface PaymentMethod {
    // Trường trong interface mặc định là hằng số public static final
    int MAX = 1000;

    String name();

    int fee(int price);
}

interface Printable {
    String print();
}

// Một lớp ký hai hợp đồng, ngăn cách bằng dấu phẩy
class Both implements PaymentMethod, Printable {
    public String name() {
        return "Both";
    }

    public int fee(int price) {
        return 0;
    }

    public String print() {
        return "printed";
    }
}
