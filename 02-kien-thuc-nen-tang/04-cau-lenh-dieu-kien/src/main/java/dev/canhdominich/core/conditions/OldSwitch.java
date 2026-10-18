package dev.canhdominich.core.conditions;

import java.util.ArrayList;
import java.util.List;

public class OldSwitch {

    public static List<String> withoutBreak(int day) {
        List<String> steps = new ArrayList<>();
        switch (day) {
            case 1:
                steps.add("Monday");
            case 2:
                steps.add("Tuesday");
            case 3:
                steps.add("Wednesday");
                break;
            default:
                steps.add("another day");
        }
        return steps;
    }

    public static String groupedCases(int month) {
        switch (month) {
            case 12:
            case 1:
            case 2:
                return "winter";
            case 3:
            case 4:
            case 5:
                return "spring";
            default:
                return "other";
        }
    }

    public static String onText(String command) {
        switch (command) {
            case "start":
                return "starting";
            case "stop":
                return "stopping";
            default:
                return "unknown command " + command;
        }
    }

    public static String nullSelector() {
        try {
            return onText(null);
        } catch (NullPointerException e) {
            return "NullPointerException: " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        System.out.println("withoutBreak(1)   = " + withoutBreak(1));
        System.out.println("withoutBreak(2)   = " + withoutBreak(2));
        System.out.println("withoutBreak(3)   = " + withoutBreak(3));
        System.out.println("withoutBreak(9)   = " + withoutBreak(9));
        System.out.println("groupedCases 12, 4, 7 = " + groupedCases(12) + ", " + groupedCases(4) + ", " + groupedCases(7));
        System.out.println("onText(\"stop\")     = " + onText("stop"));
        System.out.println("onText(\"Stop\")     = " + onText("Stop"));
        System.out.println("onText(null)       = " + nullSelector());
    }
}
