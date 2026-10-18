package dev.canhdominich.core.oop.overview;

public class SmsChannel implements Channel {

    @Override
    public String send(String to, String text) {
        return "sms to " + to + ": " + text;
    }

    @Override
    public int cost() {
        return 300;
    }

    @Override
    public boolean accepts(String address) {
        return address.matches("\\d{9,11}");
    }
}
