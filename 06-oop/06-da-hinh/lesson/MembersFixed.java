import java.util.List;
import java.util.Set;

public class MembersFixed {
    public static void main(String[] args) {
        Card a = new Card("An");
        Card b = new Card("An");
        System.out.println(a.equals(b));
        System.out.println(List.of(a).contains(b));
        System.out.println(Set.of(a).contains(b));
    }
}

class Card {
    private final String owner;

    Card(String owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        // Kiểm tra kiểu trước, rồi so sánh nội dung
        return o instanceof Card other && owner.equals(other.owner);
    }

    @Override
    public int hashCode() {
        // Cùng dữ liệu với equals để HashSet tìm đúng ngăn
        return owner.hashCode();
    }
}
