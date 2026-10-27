public class Breakages {
    static void run(String label, Runnable r) {
        try {
            r.run();
        } catch (RuntimeException e) {
            System.out.println(label + ": " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String name = "Hanoi";
        run("charAt(5)", () -> name.charAt(5));
        run("charAt(-1)", () -> name.charAt(-1));
        run("substring(4, 2)", () -> name.substring(4, 2));
        run("substring(2, 9)", () -> name.substring(2, 9));
        String input = null;
        run("null.equals", () -> input.equals("yes"));
        System.out.println("indexOf missing " + name.indexOf('z') + ", substring(1, 4) " + name.substring(1, 4) + ", indexOf o " + name.indexOf('o') + ", length " + name.length());
        String s = "banana";
        System.out.println("q2 " + s.indexOf("an") + " " + s.lastIndexOf("an") + " " + s.substring(2, 5));
        String a = "cat";
        String b = a;
        a = a + "s";
        b.toUpperCase();
        System.out.println("q3 a " + a + ", b " + b);
        String j = "Java";
        j.toUpperCase();
        j.concat("!");
        System.out.println("cc1 " + j);
        System.out.println("cc3 " + (1 + 2 + "3" + 4 + 5));
        System.out.println("b2 " + (1 + 2 + "x") + " " + ("x" + 1 + 2) + " " + ("x" + (1 + 2)) + " " + ('a' + 'b' + "c"));
        String s1 = "  Java  ";
        String t = s1.trim();
        s1.toUpperCase();
        String u = t.toUpperCase().substring(1, 3);
        System.out.println("b1 s [" + s1 + "] " + s1.length() + " t [" + t + "] u " + u);
        System.out.println("chars " + ('a' + 'b') + " " + (char) ('a' + 1) + " " + ("" + 'a' + 'b'));
    }
}
