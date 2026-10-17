package dev.canhdominich.core.io;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ScannerTraps {

    public static String naive(String input) {
        Scanner scanner = new Scanner(input);
        int age = scanner.nextInt();
        String name = scanner.nextLine();
        return "age=" + age + ", name=[" + name + "]";
    }

    public static String fixed(String input) {
        Scanner scanner = new Scanner(input);
        int age = Integer.parseInt(scanner.nextLine().trim());
        String name = scanner.nextLine();
        return "age=" + age + ", name=[" + name + "]";
    }

    public static String tokenVersusLine(String input) {
        Scanner scanner = new Scanner(input);
        String token = scanner.next();
        String rest = scanner.nextLine();
        return "next()=[" + token + "], nextLine()=[" + rest + "]";
    }

    public static String mismatch(String input) {
        Scanner scanner = new Scanner(input);
        try {
            return String.valueOf(scanner.nextInt());
        } catch (InputMismatchException e) {
            return e.getClass().getSimpleName() + ": " + e.getMessage() + ", token still there: [" + scanner.next() + "]";
        }
    }

    public static int sumAll(String input) {
        Scanner scanner = new Scanner(input);
        int sum = 0;
        while (scanner.hasNextInt()) {
            sum += scanner.nextInt();
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println("naive  (nextInt then nextLine): " + naive("25\nAn Binh\n"));
        System.out.println("fixed  (nextLine then parse)  : " + fixed("25\nAn Binh\n"));
        System.out.println("same line \"25 An Binh\"         : " + naive("25 An Binh\n"));
        System.out.println("token versus line             : " + tokenVersusLine("An Binh\n"));
        System.out.println("nextInt on \"abc\"              : " + mismatch("abc 5"));
        System.out.println("hasNextInt loop \"1 2 3 x 4\"   : " + sumAll("1 2 3 x 4"));
        System.out.println("nextInt on \"2147483648\"       : " + mismatch("2147483648"));
        try {
            new Scanner("").nextLine();
        } catch (java.util.NoSuchElementException e) {
            System.out.println("nextLine on empty input       : " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }
}
