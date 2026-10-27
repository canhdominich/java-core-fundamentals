public class Selfcheck {
    public static void main(String[] args) {
        int s = 0;
        for (int i = 1; i <= 4; i++) {
            s += i * i;
        }
        System.out.println(s);
        int n = 20;
        int count = 0;
        while (n > 1) {
            n = n / 2;
            count++;
        }
        System.out.println(n + " " + count);
        for (int i = 0; i < 3; i++); {
            System.out.println("hi");
        }
    }
}
