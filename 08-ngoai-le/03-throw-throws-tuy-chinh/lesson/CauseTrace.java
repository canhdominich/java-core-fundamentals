public class CauseTrace {
    public static void main(String[] args) {
        describe("abc");
    }

    static void describe(String id) {
        try {
            Integer.parseInt(id);
        } catch (NumberFormatException e) {
            // Không ai bắt ngoại lệ này nên dấu vết ngăn xếp có thêm dòng Caused by
            throw new IllegalStateException("cannot read order " + id, e);
        }
    }
}
