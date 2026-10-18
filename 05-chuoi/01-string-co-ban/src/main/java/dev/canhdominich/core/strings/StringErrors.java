package dev.canhdominich.core.strings;

public class StringErrors {

    public static void main(String[] args) {
        String indexing = "class A { char f(String s) { return s[0]; } }";
        String lengthField = "class A { int f(String s) { return s.length; } }";
        String twoChars = "class A { char c = 'ab'; }";
        String unclosed = "class A { String s = \"abc; }";
        String charToString = "class A { String s = 'a'; }";
        String assignChar = "class A { void f(String s) { s.charAt(0) = 'x'; } }";
        String missingMethod = "class A { String f(String s) { return s.reverse(); } }";
        System.out.println("s[0] on a String      = " + Javac.messages("A", indexing));
        System.out.println("s.length without ()   = " + Javac.messages("A", lengthField));
        System.out.println("two chars in quotes   = " + Javac.messages("A", twoChars));
        System.out.println("missing closing quote = " + Javac.messages("A", unclosed));
        System.out.println("char into a String    = " + Javac.messages("A", charToString));
        System.out.println("assign to charAt      = " + Javac.messages("A", assignChar));
        System.out.println("no reverse() method   = " + Javac.messages("A", missingMethod));
    }
}
