package dev.canhdominich.core.oop.overview;

public class EmailChannel implements Channel {

    @Override
    public String send(String to, String text) {
        return "email to " + to + ": " + text;
    }

    @Override
    public int cost() {
        return 1;
    }

    @Override
    public boolean accepts(String address) {
        return address.contains("@");
    }
}
