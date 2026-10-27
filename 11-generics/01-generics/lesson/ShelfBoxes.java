import java.util.List;

public class ShelfBoxes {
    static class Box<T> {
        private T content;

        Box(T content) {
            this.content = content;
        }

        T get() {
            return content;
        }

        void set(T content) {
            this.content = content;
        }
    }

    // T được suy ra từ phần tử của danh sách
    static <T> T last(List<T> items) {
        return items.get(items.size() - 1);
    }

    static <T> Box<T> boxOf(T value) {
        return new Box<>(value);
    }

    public static void main(String[] args) {
        Box<String> drink = new Box<>("Latte");
        System.out.println("step 1: " + drink.get().length());
        Box<Integer> cups = new Box<>(3);
        System.out.println("step 2: " + (cups.get() + 1));
        drink.set("Mocha");
        System.out.println("step 3: " + drink.get());
        System.out.println("step 4: " + last(List.of("a", "b", "c")));
        System.out.println("step 5: " + boxOf(42).get() + boxOf("x").get());
    }
}
