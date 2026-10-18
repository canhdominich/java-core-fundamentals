package dev.canhdominich.core.strings;

import java.util.Objects;

public class StringCompare {

    public static String equality() {
        String literal = "java";
        String sameLiteral = "java";
        String created = new String("java");
        return "literal == literal " + (literal == sameLiteral) + ", literal == new String " + (literal == created)
                + ", equals " + literal.equals(created) + ", interned == literal " + (created.intern() == literal);
    }

    public static String caseAndNull(String input) {
        boolean unsafe;
        try {
            unsafe = input.equals("yes");
        } catch (NullPointerException e) {
            return "input.equals throws NullPointerException; \"yes\".equals(input) " + "yes".equals(input) + "; Objects.equals " + Objects.equals(input, "yes");
        }
        return "input.equals " + unsafe + ", \"yes\".equals " + "yes".equals(input);
    }

    public static String ignoringCase() {
        return "equalsIgnoreCase " + "Java".equalsIgnoreCase("JAVA") + ", equals " + "Java".equals("JAVA");
    }

    public static String ordering() {
        return "apple vs banana " + "apple".compareTo("banana") + ", banana vs apple " + "banana".compareTo("apple")
                + ", apple vs apple " + "apple".compareTo("apple") + ", apple vs apples " + "apple".compareTo("apples")
                + ", Zebra vs apple " + "Zebra".compareTo("apple") + ", ignoring case " + "Zebra".compareToIgnoreCase("apple");
    }

    public static String compileTimeConstants() {
        String a = "ja";
        String b = "va";
        final String fa = "ja";
        return "\"ja\" + \"va\" == \"java\" " + (("ja" + "va") == "java") + ", a + b == \"java\" " + ((a + b) == "java")
                + ", final fa + \"va\" == \"java\" " + ((fa + "va") == "java");
    }

    public static void main(String[] args) {
        System.out.println("equality         = " + equality());
        System.out.println("null input       = " + caseAndNull(null));
        System.out.println("non-null input   = " + caseAndNull("yes"));
        System.out.println("ignoring case    = " + ignoringCase());
        System.out.println("ordering         = " + ordering());
        System.out.println("compile-time constants = " + compileTimeConstants());
    }
}
