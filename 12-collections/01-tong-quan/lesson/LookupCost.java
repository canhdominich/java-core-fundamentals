import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LookupCost {
    // Bộ đếm số lần equals bị gọi
    static int calls = 0;

    static class Key {
        final int id;

        Key(int id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            calls++;  // mỗi lần so sánh được tính một lần
            return o instanceof Key && ((Key) o).id == id;
        }

        @Override
        public int hashCode() {
            return id;  // mỗi khoá một mã băm riêng nên rơi vào ngăn riêng
        }
    }

    public static void main(String[] args) {
        List<Key> list = new ArrayList<>();
        Set<Key> set = new HashSet<>();
        for (int i = 0; i < 100000; i++) {
            list.add(new Key(i));
            set.add(new Key(i));
        }
        calls = 0;  // xoá bộ đếm để không tính lẫn lúc nạp dữ liệu
        list.contains(new Key(99999));  // khoá cuối: trường hợp xấu nhất của việc duyệt
        System.out.println("list equals calls: " + calls);
        calls = 0;
        set.contains(new Key(99999));
        System.out.println("set equals calls: " + calls);
    }
}
