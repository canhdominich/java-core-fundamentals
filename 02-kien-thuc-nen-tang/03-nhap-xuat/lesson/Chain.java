import java.util.Scanner;

public class Chain {
    public static void main(String[] args) {
        Scanner in = new Scanner("10 20 abc 30");
        int first = in.nextInt();
        int second = in.nextInt();
        System.out.println(first + " " + second);
        System.out.println(in.hasNextInt()); // token kế tiếp là abc nên false
        System.out.println(in.next()); // bỏ abc
        System.out.println(in.nextInt());
    }
}
