public class Trace {
    public static void main(String[] args) {
        int a = 10;
        int b = a;
        a = 20;
        int[] x = {1};
        int[] y = x;
        y[0] = 5;
        System.out.println(a + " " + b + " " + x[0] + " " + y[0]);
    }
}
