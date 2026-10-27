public class Selfcheck {
    public static void main(String[] args) {
        int a = 2_000_000_000;
        int b = a + a;
        long c = a + a;
        long d = (long) a + a;
        System.out.println(b + " " + c + " " + d);
        double total = 0.1 + 0.2;
        System.out.println(Math.abs(total - 0.3) < 1e-9);
        int[] p = {1, 2};
        int[] q = p;
        q[1] = 7;
        p = new int[] {5, 5};
        System.out.println(p[0] + " " + p[1] + " " + q[0] + " " + q[1]);
    }
}
