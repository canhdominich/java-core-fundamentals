import java.util.HashSet;
import java.util.List;

public class Fragile {
    public static void main(String[] args) {
        CountingSet set = new CountingSet();
        set.addAll(List.of("a", "b", "c"));
        System.out.println(set.size() + " elements, counter " + set.added);
    }
}

class CountingSet extends HashSet<String> {
    int added = 0;

    @Override
    public boolean add(String s) {
        added++;
        return super.add(s);
    }

    @Override
    public boolean addAll(java.util.Collection<? extends String> c) {
        added += c.size();
        return super.addAll(c);
    }
}
