import java.util.Arrays;
import java.util.Comparator;

public class SortKinds {
    public static void main(String[] args) {
        String[] words = {"Bo", "An", "Cu", "Binh", "Ai", "Chau", "Do"};
        Arrays.sort(words, Comparator.comparingInt(String::length));
        System.out.println(Arrays.toString(words));

        // 1000 phần tử chỉ có 3 khoá: "0:0", "1:1", "2:2", "0:3", ...
        String[] items = new String[1000];
        for (int i = 0; i < items.length; i++) {
            items[i] = (i % 3) + ":" + i;
        }
        Arrays.sort(items, Comparator.comparingInt((String s) -> s.charAt(0)));

        boolean stable = true;
        for (int i = 1; i < items.length; i++) {
            String before = items[i - 1];
            String after = items[i];
            boolean sameKey = before.charAt(0) == after.charAt(0);
            // Cùng khoá thì thứ tự nhập ban đầu (số sau dấu hai chấm) phải còn tăng dần
            if (sameKey && Integer.parseInt(before.substring(2)) > Integer.parseInt(after.substring(2))) {
                stable = false;
            }
        }
        System.out.println("stable after sorting 1000 items by key: " + stable);
        System.out.println("first four: " + items[0] + " " + items[1] + " " + items[2] + " " + items[3]);
    }
}
