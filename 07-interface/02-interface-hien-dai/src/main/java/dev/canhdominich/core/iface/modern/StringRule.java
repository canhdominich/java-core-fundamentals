package dev.canhdominich.core.iface.modern;

@FunctionalInterface
public interface StringRule {

    boolean test(String text);

    default StringRule and(StringRule other) {
        return text -> test(text) && other.test(text);
    }

    default StringRule negate() {
        return text -> !test(text);
    }

    static StringRule notBlank() {
        return text -> text != null && !text.isBlank();
    }

    static StringRule maxLength(int max) {
        return text -> text.length() <= max;
    }
}
