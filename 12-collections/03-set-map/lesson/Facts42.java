import java.util.*;

public class Facts42 {
    static class Ticket {
        final String code;
        final int seat;
        Ticket(String code, int seat) { this.code = code; this.seat = seat; }
        @Override public boolean equals(Object o) {
            if (!(o instanceof Ticket)) return false;
            Ticket t = (Ticket) o;
            return code.equals(t.code) && seat == t.seat;
        }
        @Override public int hashCode() { return Objects.hash(code, seat); }
    }
    static class Cup {
        String name;
        Cup(String name) { this.name = name; }
        @Override public boolean equals(Object o) { return o instanceof Cup && ((Cup) o).name.equals(name); }
        @Override public int hashCode() { return name.hashCode(); }
    }
    public static void main(String[] args) {
        Set<Ticket> s = new HashSet<>();
        s.add(new Ticket("A1", 3));
        System.out.println(s.contains(new Ticket("A1", 3)) + " " + s.add(new Ticket("A1", 3)));
        Cup c = new Cup("Latte");
        Map<Cup, Integer> m = new HashMap<>();
        m.put(c, 1);
        c.name = "Mocha";
        System.out.println(m.get(c) + " " + m.get(new Cup("Latte")) + " " + m.size());
        Map<String, Integer> tm = new TreeMap<>();
        for (String d : List.of("Latte", "Mocha", "Latte", "Tea")) tm.merge(d, 1, Integer::sum);
        System.out.println(tm);
        Map<String, Integer> lhm = new LinkedHashMap<>();
        lhm.put("a", 1); lhm.put("b", 2); lhm.put("a", 3);
        System.out.println(lhm);
        for (Map.Entry<Integer, String> e : new TreeMap<>(Map.of(8, "Mocha", 7, "Latte")).entrySet()) System.out.println(e.getKey() + "=" + e.getValue());
    }
}
