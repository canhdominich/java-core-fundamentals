import java.util.ArrayList;
import java.util.List;

public class Selfcheck {
    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>(List.of(5, 6, 7));
        a.add(1, 9);
        a.remove(2);
        a.set(0, a.get(2));
        System.out.println(a);

        List<Integer> b = new ArrayList<>(List.of(1, 2, 3, 4));
        for (int i = 0; i < b.size(); i++) {
            if (b.get(i) < 4) {
                b.remove(i);
            }
        }
        System.out.println(b);
    }
}
