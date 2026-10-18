package dev.canhdominich.core.conditions;

public class SwitchExpressions {

    public static String kind(Day day) {
        return switch (day) {
            case SATURDAY, SUNDAY -> "weekend";
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "weekday";
        };
    }

    public static int length(Day day) {
        return switch (day) {
            case MONDAY, FRIDAY, SUNDAY -> 6;
            case TUESDAY -> 7;
            case THURSDAY, SATURDAY -> 8;
            case WEDNESDAY -> {
                int letters = day.name().length();
                yield letters;
            }
        };
    }

    public static String statusText(int code) {
        return switch (code / 100) {
            case 2 -> "success";
            case 3 -> "redirect";
            case 4, 5 -> {
                String side = code / 100 == 4 ? "client" : "server";
                yield side + " error";
            }
            default -> "unknown";
        };
    }

    public static void main(String[] args) {
        for (Day day : Day.values()) {
            System.out.printf("%-10s kind=%-8s length=%d%n", day, kind(day), length(day));
        }
        System.out.println("statusText 200, 301, 404, 503, 99 = " + statusText(200) + ", " + statusText(301) + ", " + statusText(404) + ", " + statusText(503) + ", " + statusText(99));
        System.out.println("no fall-through: statements run once per matching label");
        String wrongKinds = "class A { int f(int x) { return switch (x) { case 1 -> 10; case 2: yield 20; default -> 0; }; } }";
        String notExhaustive = "class A { int f(int x) { return switch (x) { case 1 -> 10; case 2 -> 20; }; } }";
        String duplicate = "class A { int f(int x) { switch (x) { case 1: return 1; case 1: return 2; } return 0; } }";
        String missingYield = "class A { int f(int x) { return switch (x) { case 1 -> { int y = 5; } default -> 0; }; } }";
        System.out.println("mixing -> and :        " + Javac.messages("A", wrongKinds));
        System.out.println("expression, no default: " + Javac.messages("A", notExhaustive));
        System.out.println("duplicate label:        " + Javac.messages("A", duplicate));
        System.out.println("block without yield:    " + Javac.messages("A", missingYield));
    }
}
