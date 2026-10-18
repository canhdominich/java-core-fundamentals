package dev.canhdominich.core.oop.overview;

public class PushChannel implements Channel {

    @Override
    public String send(String to, String text) {
        return "push to " + to + ": " + text;
    }

    @Override
    public int cost() {
        return 0;
    }

    @Override
    public boolean accepts(String address) {
        return !address.isBlank();
    }
}
