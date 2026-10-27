import java.math.BigDecimal;
import java.util.*;

public class Facts43 {
    static class Order {
        final String customer; final int priority;
        Order(String c, int p) { customer = c; priority = p; }
        public String toString() { return customer + ":" + priority; }
    }
    static class Natural implements Comparable<Natural> {
        final String c; final int p;
        Natural(String c, int p) { this.c = c; this.p = p; }
        public int compareTo(Natural o) { return Integer.compare(o.p, p); }
        public String toString() { return c + ":" + p; }
    }
    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>(List.of(1, 2, 2, 3));
        Iterator<Integer> it = a.iterator();
        while (it.hasNext()) { if (it.next() == 2) it.remove(); }
        System.out.println(a);
        List<Order> o = new ArrayList<>(List.of(new Order("Lan", 2), new Order("Minh", 1), new Order("An", 2), new Order("Khoa", 3), new Order("Binh", 1)));
        Comparator<Order> byP = Comparator.comparingInt(x -> x.priority);
        Comparator<Order> byN = Comparator.comparing(x -> x.customer);
        List<Order> o1 = new ArrayList<>(o); o1.sort(byP.thenComparing(byN)); System.out.println(o1);
        List<Order> o2 = new ArrayList<>(o); o2.sort(byP.reversed().thenComparing(byN)); System.out.println(o2);
        List<Natural> n = new ArrayList<>(List.of(new Natural("Chi", 2), new Natural("An", 3), new Natural("Binh", 2), new Natural("Dung", 1)));
        Collections.sort(n); System.out.println(n);
        List<Order> t = new ArrayList<>(List.of(new Order("Chi", 2), new Order("An", 3), new Order("Binh", 2), new Order("Dung", 1)));
        t.sort(byP); t.sort(byN); System.out.println(t);
        List<String> ns = new ArrayList<>(Arrays.asList("pear", null, "apple"));
        try { Collections.sort(ns); } catch (NullPointerException e) { System.out.println("NPE"); }
        ns.sort(Comparator.nullsFirst(Comparator.naturalOrder())); System.out.println(ns);
        BigDecimal x = new BigDecimal("1.0"), y = new BigDecimal("1.00");
        System.out.println(x.equals(y) + " " + x.compareTo(y) + " " + new HashSet<>(List.of(x, y)).size() + " " + new TreeSet<>(List.of(x, y)).size());
    }
}
