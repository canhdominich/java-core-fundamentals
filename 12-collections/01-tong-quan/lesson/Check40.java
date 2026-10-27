import java.util.*;

public class Check40 {
    public static void main(String[] args) {
        List<String> a = new ArrayList<>(List.of("Latte", "Mocha", "Latte"));
        Set<String> b = new HashSet<>(a);
        Map<Integer, String> c = new HashMap<>();
        System.out.println(a.size() + " " + b.size() + " " + c.size());
        c.put(1, a.get(0));
        c.put(2, a.get(1));
        System.out.println(c.size() + " " + c.get(1));
        c.put(1, "Tea");
        System.out.println(c.size() + " " + c.get(1));
        System.out.println(b.add("Mocha") + " " + b.size());

        List<String> in = new ArrayList<>(List.of("Tea", "Latte", "Tea", "Mocha", "Latte"));
        List<String> unique = new ArrayList<>(new LinkedHashSet<>(in));
        List<String> sorted = new ArrayList<>(new TreeSet<>(in));
        List<String> frozen = List.copyOf(unique);
        System.out.println(unique + " " + sorted + " " + frozen);
        try { frozen.add("X"); } catch (UnsupportedOperationException e) { System.out.println("UOE"); }

        Set<String> s = new LinkedHashSet<>();
        System.out.println(s.add("Tea") + " " + s.add("Latte") + " " + s.add("Tea") + " " + s);

        List<String> src = new ArrayList<>(List.of("A", "B"));
        List<String> view = Collections.unmodifiableList(src);
        List<String> copy = List.copyOf(src);
        src.add("C");
        System.out.println(view.size() + " " + copy.size());

        try { List.of("a", null); } catch (NullPointerException e) { System.out.println("List.of null NPE"); }
        try { new TreeMap<String, Integer>().put(null, 1); } catch (NullPointerException e) { System.out.println("TreeMap null NPE"); }
        Map<String, Integer> hm = new HashMap<>();
        hm.put(null, 1);
        System.out.println("HashMap null ok " + hm.get(null));

        Set<String> uniq = new LinkedHashSet<>();
        int added = 0;
        for (String id : List.of("o1", "o2", "o1", "o3", "o2")) {
            if (uniq.add(id)) { added++; }
        }
        System.out.println(added + " " + uniq);
    }
}
