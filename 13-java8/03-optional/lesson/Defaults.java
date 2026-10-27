import java.util.Optional;

public class Defaults {
    static int built = 0;

    static String makeDefault() {
        built++;
        return "default";
    }

    public static void main(String[] args) {
        Optional<String> present = Optional.of("An");
        Optional<String> empty = Optional.empty();

        present.orElse(makeDefault());  // đối số được tính trước, dù hộp đã có giá trị
        System.out.println("orElse on present: built " + built);
        present.orElseGet(() -> makeDefault());  // chỉ chạy khi hộp rỗng
        System.out.println("orElseGet on present: built " + built);
        empty.orElseGet(() -> makeDefault());
        System.out.println("orElseGet on empty: built " + built);
    }
}
