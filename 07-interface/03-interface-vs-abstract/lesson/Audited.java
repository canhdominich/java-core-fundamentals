import java.util.ArrayList;
import java.util.List;

public class Audited {
    public static void main(String[] args) {
        AuditedCard card = new AuditedCard();
        card.receipt(100);
        card.receipt(200);
        System.out.println(card.log());
    }
}

interface Auditable {
    List<String> log();
}

// Lớp nền xây dở: có dữ liệu và code dùng chung, còn thiếu một phần
abstract class BasePayment {
    // Mỗi đối tượng giữ tên riêng, interface không làm được việc này
    private final String name;

    BasePayment(String name) { this.name = name; }

    // Chưa có thân: mỗi lớp con phải tự viết
    abstract int fee(int price);

    // Viết một lần, gọi fee của lớp con thật khi chạy
    String receipt(int price) { return name + " total " + (price + fee(price)); }
}

class AuditedCard extends BasePayment implements Auditable {
    // Nhật ký riêng của từng đối tượng
    private final List<String> lines = new ArrayList<>();

    AuditedCard() { super("Card"); }

    // Phí 2 phần trăm
    int fee(int price) { return price * 2 / 100; }

    String receipt(int price) {
        // Dùng lại thân receipt của lớp cha rồi ghi thêm vào nhật ký
        String line = super.receipt(price);
        lines.add(line);
        return line;
    }

    public List<String> log() { return lines; }
}
