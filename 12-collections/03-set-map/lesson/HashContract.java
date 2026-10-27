import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class HashContract {
    // Chỉ ghi đè equals, thiếu hashCode
    static class BadOrder {
        final int customer;
        final String drink;

        BadOrder(int customer, String drink) {
            this.customer = customer;
            this.drink = drink;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof BadOrder)) {
                return false;
            }
            BadOrder other = (BadOrder) o;
            return customer == other.customer && drink.equals(other.drink);
        }
    }

    // Ghi đè cả equals và hashCode trên cùng các trường
    static class GoodOrder {
        final int customer;
        final String drink;

        GoodOrder(int customer, String drink) {
            this.customer = customer;
            this.drink = drink;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof GoodOrder)) {
                return false;
            }
            GoodOrder other = (GoodOrder) o;
            return customer == other.customer && drink.equals(other.drink);
        }

        @Override
        public int hashCode() {
            return Objects.hash(customer, drink);  // đúng hai trường mà equals dùng
        }
    }

    public static void main(String[] args) {
        // Bản sao có mã băm mặc định khác nên lọt sang ngăn khác
        Set<BadOrder> bad = new HashSet<>();
        bad.add(new BadOrder(7, "Latte"));
        System.out.println("bad:  contains " + bad.contains(new BadOrder(7, "Latte")) + ", add again " + bad.add(new BadOrder(7, "Latte")) + ", size " + bad.size());

        Set<GoodOrder> good = new HashSet<>();
        good.add(new GoodOrder(7, "Latte"));
        System.out.println("good: contains " + good.contains(new GoodOrder(7, "Latte")) + ", add again " + good.add(new GoodOrder(7, "Latte")) + ", size " + good.size());
    }
}
