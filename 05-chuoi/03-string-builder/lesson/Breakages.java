public class Breakages {
    static void run(String label, Runnable r) {
        try {
            r.run();
        } catch (RuntimeException e) {
            System.out.println(label + ": " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        run("insert(5)", () -> new StringBuilder("abc").insert(5, "x"));
        run("deleteCharAt(3)", () -> new StringBuilder("abc").deleteCharAt(3));
        StringBuilder sb = new StringBuilder("abc");
        sb.append("de");
        sb.insert(1, "X");
        sb.deleteCharAt(0);
        String s = sb.toString();
        sb.reverse();
        System.out.println("b1 s " + s + ", sb " + sb);
        StringBuilder q = new StringBuilder("abc");
        q.append(1).append('d');
        q.insert(0, "x");
        q.reverse();
        System.out.println("q2 " + q);
        StringBuilder cc = new StringBuilder("ab");
        String snap = cc.toString();
        cc.append("c");
        System.out.println("cc1 " + snap + " " + cc);
        System.out.println("delete(1, 99) is allowed: " + new StringBuilder("abc").delete(1, 99));
    }
}
