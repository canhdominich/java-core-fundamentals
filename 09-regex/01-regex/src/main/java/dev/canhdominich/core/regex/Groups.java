package dev.canhdominich.core.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Groups {

    private static final Pattern DATE = Pattern.compile("(?<year>\\d{4})-(?<month>\\d{2})-(?<day>\\d{2})");

    private Groups() {
    }

    public static String parseDate(String text) {
        Matcher matcher = DATE.matcher(text);
        if (!matcher.matches()) {
            return "not a date: " + text;
        }
        return "year " + matcher.group("year") + ", month " + matcher.group("month") + ", day " + matcher.group("day")
                + ", whole " + matcher.group(0) + ", group count " + matcher.groupCount();
    }

    public static String numberedGroups() {
        Matcher matcher = Pattern.compile("(\\w+)@(\\w+)\\.com").matcher("write to an@example.com today");
        matcher.find();
        return "group 0 " + matcher.group(0) + ", group 1 " + matcher.group(1) + ", group 2 " + matcher.group(2)
                + ", span " + matcher.start() + " to " + matcher.end();
    }

    public static String replacing() {
        return "swap " + "Nguyen Van An".replaceAll("(\\w+) (\\w+) (\\w+)", "$3 $1 $2")
                + ", mask digits " + "card 1234 5678".replaceAll("\\d", "#")
                + ", collapse spaces " + "a   b    c".replaceAll("\\s+", " ")
                + ", named " + "2026-10-18".replaceAll("(?<y>\\d{4})-(?<m>\\d{2})-(?<d>\\d{2})", "${d}/${m}/${y}");
    }

    public static String splitting() {
        return "split on commas with spaces " + java.util.Arrays.toString("a, b,c ,  d".split("\\s*,\\s*"))
                + ", split keeps the lookahead " + java.util.Arrays.toString("camelCaseWord".split("(?=[A-Z])"));
    }

    public static String optionalGroup() {
        Matcher matcher = Pattern.compile("(\\d+)(px)?").matcher("12");
        matcher.matches();
        return "optional group is " + matcher.group(2) + " when absent";
    }
}
