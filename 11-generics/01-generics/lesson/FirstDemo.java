import java.util.List;

public class FirstDemo {
    // T là nhãn do nơi gọi quyết định, kiểu trả về luôn khớp kiểu của danh sách
    static <T> T first(List<T> items) {
        return items.get(0);
    }

    public static void main(String[] args) {
        String drink = first(List.of("Latte", "Mocha"));
        int price = first(List.of(10, 25));
        System.out.println(drink + " " + price);
    }
}
