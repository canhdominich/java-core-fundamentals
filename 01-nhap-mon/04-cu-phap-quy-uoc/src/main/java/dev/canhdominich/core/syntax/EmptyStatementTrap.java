package dev.canhdominich.core.syntax;

public class EmptyStatementTrap {

    static final String TRAP = """
            public class A {
                static String label(int age) {
                    String result = "child";
                    if (age >= 18); {
                        result = "adult";
                    }
                    return result;
                }
            }
            """;

    static String label(int age) {
        String result = "child";
        if (age >= 18);
        {
            result = "adult";
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("label(10) = " + label(10) + "   (a ten-year-old became an adult)");
        System.out.println("label(30) = " + label(30));
        System.out.println("default compile : " + SourceChecks.compile("A", TRAP).messages());
        System.out.println("with -Xlint:empty: " + SourceChecks.compile("A", TRAP, "-Xlint:empty").messages());
    }
}
