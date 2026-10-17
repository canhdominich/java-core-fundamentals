package dev.canhdominich.core.hello;

public final class BrokenPrograms {

    public static final String MISSING_SEMICOLON = """
            public class Hello {
                public static void main(String[] args) {
                    System.out.println("Hello")
                }
            }
            """;

    public static final String UNKNOWN_SYMBOL = """
            public class Hello {
                public static void main(String[] args) {
                    System.out.println(mesage);
                }
            }
            """;

    public static final String WRONG_FILE_NAME = """
            public class Wrong {
                public static void main(String[] args) {
                    System.out.println("Hello");
                }
            }
            """;

    public static final String NO_MAIN = """
            public class Hello {
                public void run() {
                    System.out.println("Hello");
                }
            }
            """;

    public static final String THROWS_AT_RUNTIME = """
            public class Hello {
                public static void main(String[] args) {
                    int[] values = new int[2];
                    System.out.println(values[5]);
                }
            }
            """;

    public static final String EXIT_THREE = """
            public class Hello {
                public static void main(String[] args) {
                    System.out.println("about to exit");
                    System.exit(3);
                }
            }
            """;

    private BrokenPrograms() {
    }
}
