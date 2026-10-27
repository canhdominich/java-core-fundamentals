public class Clocked {
    public static void main(String[] args) {
        Receipt receipt = new Receipt(new FixedClock(1700000000000L));
        System.out.println(receipt.issue(110));
    }
}

// Hợp đồng đồng hồ, khi kiểm thử có thể thay bằng giờ cố định
interface Clock {
    long now();
}

class FixedClock implements Clock {
    private final long value;

    FixedClock(long value) {
        this.value = value;
    }

    public long now() {
        return value;
    }
}

class Receipt {
    private final Clock clock;

    // Đồng hồ do bên ngoài đưa vào, lớp không tự đọc giờ hệ thống
    Receipt(Clock clock) {
        this.clock = clock;
    }

    String issue(int amount) {
        return "paid " + amount + " at " + clock.now();
    }
}
