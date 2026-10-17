package dev.canhdominich.core.intro;

public final class Sources {

    public static final String GREETER = """
            public class Greeter {
                public static String greet(String name) {
                    return "Hello, " + name;
                }
            }
            """;

    private Sources() {
    }
}
