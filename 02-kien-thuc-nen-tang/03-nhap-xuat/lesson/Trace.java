import java.util.Scanner;

public class Trace {
    public static void main(String[] args) {
        Scanner in = new Scanner("7 8\nHi\n");
        int a = in.nextInt();
        int b = in.nextInt();
        String s = in.nextLine();
        String t = in.nextLine();
        System.out.println(a + " " + b + " [" + s + "] [" + t + "]");
    }
}
