import java.util.*;

public class Check42 {
    public static void main(String[] args) {
        Set<String> s = new HashSet<>();
        s.add("A1"); s.add("A2"); s.add("A1"); s.remove("A2"); s.add("A3");
        Map<String, Integer> m = new HashMap<>();
        m.put("x", 1); m.put("y", 2); m.put("x", 3); m.remove("y"); m.put("z", m.getOrDefault("y", 0) + 5);
        System.out.println(new TreeSet<>(s) + " " + new TreeMap<>(m));

        Map<String, Integer> t = new TreeMap<>();
        t.put("b", 1); t.put("a", 2); t.put("b", 3);
        System.out.println(t + " " + t.size() + " " + t.get("c"));

        Set<Integer> ts = new TreeSet<>(List.of(5, 1, 3));
        ts.add(3); ts.add(2); ts.remove(5);
        System.out.println(ts);

        Map<String, Integer> count = new TreeMap<>();
        for (String d : List.of("Latte", "Mocha", "Latte", "Tea")) {
            count.merge(d, 1, Integer::sum);
        }
        System.out.println(count);
        Map<Integer, String> lm = new LinkedHashMap<>();
        lm.put(3, "c"); lm.put(1, "a"); lm.put(3, "z");
        System.out.println(lm);
        Set<Integer> hs = new HashSet<>(List.of(3, 1, 2));
        System.out.println(hs);
        System.out.println(new HashMap<>(Map.of("a", 1)).put("a", 2));
    }
}
