import java.util.ArrayList;
import java.util.List;

public class Trace {
    public static void main(String[] args) {
        List<String> q = new ArrayList<>(List.of("A", "B", "C", "D"));
        q.add(2, "X");
        q.remove("B");
        q.set(0, "Z");
        q.remove(q.size() - 1);
        System.out.println(q + " size " + q.size());
    }
}
