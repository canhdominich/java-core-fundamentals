import java.util.HashSet;
import java.util.Set;

public class NoHash {
    public static void main(String[] args) {
        Set<Coupon> set = new HashSet<>();
        set.add(new Coupon("TEA10"));
        System.out.println(set.contains(new Coupon("TEA10")));
    }
}

class Coupon {
    private final String code;

    Coupon(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Coupon other && code.equals(other.code);
    }
}
