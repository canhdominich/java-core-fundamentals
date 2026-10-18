package dev.canhdominich.core.oop.overview;

public final class ProceduralNotifier {

    private ProceduralNotifier() {
    }

    public static String send(String channel, String to, String text) {
        switch (channel) {
            case "email":
                return "email to " + to + ": " + text;
            case "sms":
                return "sms to " + to + ": " + text;
            case "push":
                return "push to " + to + ": " + text;
            default:
                throw new IllegalArgumentException("unknown channel: " + channel);
        }
    }

    public static int cost(String channel) {
        switch (channel) {
            case "email":
                return 1;
            case "sms":
                return 300;
            case "push":
                return 0;
            default:
                throw new IllegalArgumentException("unknown channel: " + channel);
        }
    }

    public static boolean accepts(String channel, String address) {
        switch (channel) {
            case "email":
                return address.contains("@");
            case "sms":
                return address.matches("\\d{9,11}");
            case "push":
                return !address.isBlank();
            default:
                throw new IllegalArgumentException("unknown channel: " + channel);
        }
    }
}
