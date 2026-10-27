public class ShelfDemo {
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

    public static void main(String[] args) {
        // Nhãn String ghi ngay ở khai báo, dấu <> để Java tự suy ra phần còn lại
        Box<String> drink = new Box<>("Latte");
        Box<Integer> cups = new Box<>(3);
        // get() trả đúng String nên không cần ép kiểu
        String name = drink.get();
        int count = cups.get();
        System.out.println(name.length() + " " + (count + 1));
    }
}
