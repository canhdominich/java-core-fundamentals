package dev.canhdominich.core.syntax;

import java.util.regex.Pattern;

public final class NamingConventions {

    public enum Kind {
        CLASS("[A-Z][a-zA-Z0-9]*"),
        METHOD_OR_VARIABLE("[a-z][a-zA-Z0-9]*"),
        CONSTANT("[A-Z][A-Z0-9]*(_[A-Z0-9]+)*"),
        PACKAGE("[a-z][a-z0-9]*(\\.[a-z][a-z0-9]*)*");

        private final Pattern pattern;

        Kind(String regex) {
            this.pattern = Pattern.compile(regex);
        }

        public boolean accepts(String name) {
            return pattern.matcher(name).matches();
        }
    }

    private NamingConventions() {
    }

    public static boolean follows(Kind kind, String name) {
        return kind.accepts(name);
    }
}
