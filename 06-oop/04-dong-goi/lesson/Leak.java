import java.util.ArrayList;
import java.util.List;

public class Leak {
    public static void main(String[] args) {
        Card card = new Card();
        card.log("earn 20");
        card.getHistory().add("hacked");
        System.out.println(card.getHistory());

        SafeCard safe = new SafeCard();
        safe.log("earn 20");
        try {
            safe.getHistory().add("hacked");
        } catch (UnsupportedOperationException e) {
            System.out.println("blocked: " + e);
        }
        System.out.println(safe.getHistory());
    }
}

class Card {
    private final List<String> history = new ArrayList<>();

    void log(String note) {
        history.add(note);
    }

    List<String> getHistory() {
        // Trả chính danh sách bên trong nên bên ngoài sửa được
        return history;
    }
}

class SafeCard {
    private final List<String> history = new ArrayList<>();

    void log(String note) {
        history.add(note);
    }

    List<String> getHistory() {
        // Trả bản sao chỉ đọc nên bên ngoài không sửa được
        return List.copyOf(history);
    }
}
