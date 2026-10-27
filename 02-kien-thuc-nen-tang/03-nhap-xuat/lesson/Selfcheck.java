import java.util.Scanner;

public class Selfcheck {
    public static void main(String[] args) {
        System.out.printf("%5d|%-5d|%05d%n", 42, 42, 42);
        Scanner in = new Scanner("12 apples\n7\n");
        int a = in.nextInt();
        String w = in.next();
        int b = in.nextInt();
        System.out.println(a + " " + w + " " + b);
        System.out.println(in.hasNextLine() + " [" + in.nextLine() + "]");
    }
}
