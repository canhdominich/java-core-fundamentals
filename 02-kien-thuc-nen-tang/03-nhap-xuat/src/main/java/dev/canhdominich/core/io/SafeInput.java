package dev.canhdominich.core.io;

import java.io.PrintStream;
import java.util.Scanner;

public final class SafeInput {

    private SafeInput() {
    }

    public static int readInt(Scanner scanner, PrintStream out, String prompt, int min, int max) {
        while (true) {
            out.print(prompt);
            if (!scanner.hasNextLine()) {
                throw new IllegalStateException("input ended before a valid number");
            }
            String line = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(line);
                if (value >= min && value <= max) {
                    return value;
                }
                out.println("must be between " + min + " and " + max);
            } catch (NumberFormatException e) {
                out.println("not a whole number: " + line);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int age = readInt(scanner, System.out, "age (0-120): ", 0, 120);
        System.out.print("name: ");
        String name = scanner.nextLine();
        System.out.println("Hello " + name + ", you are " + age);
    }
}
