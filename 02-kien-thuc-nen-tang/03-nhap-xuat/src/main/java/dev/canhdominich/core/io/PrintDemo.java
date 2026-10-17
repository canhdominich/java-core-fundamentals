package dev.canhdominich.core.io;

public class PrintDemo {

    public static void main(String[] args) {
        System.out.print("no newline|");
        System.out.println("println adds one");
        System.out.printf("printf with %s and %d%n", "text", 42);
        System.out.println('a' + 'b');
        System.out.println("" + 'a' + 'b');
        System.out.println('a');
        System.out.println(1 + 2 + "3");
        System.out.println((String) null);
        System.out.println((Object) null);
        System.out.printf("%s|%s%n", null, "x");
        char[] letters = {'J', 'a', 'v', 'a'};
        System.out.println(letters);
        System.out.println("letters: " + String.valueOf(letters));
        System.out.println("tab\there, quote \" and backslash \\ and unicode é");
        System.out.println("line one\nline two");
        System.out.print("no trailing newline at the very end");
        System.out.println();
        System.out.println("text block:");
        System.out.println("""
                first
                  second
                """.stripTrailing());
    }
}
