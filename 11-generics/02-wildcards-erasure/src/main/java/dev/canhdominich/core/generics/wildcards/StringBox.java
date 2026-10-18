package dev.canhdominich.core.generics.wildcards;

public class StringBox implements Comparable<StringBox> {

    private final String text;

    public StringBox(String text) {
        this.text = text;
    }

    @Override
    public int compareTo(StringBox other) {
        return text.compareTo(other.text);
    }
}
