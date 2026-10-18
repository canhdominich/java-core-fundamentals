package dev.canhdominich.core.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Basics {

    private Basics() {
    }

    public static String threeQuestions() {
        Pattern pattern = Pattern.compile("\\d+");
        String text = "order 42 shipped";
        Matcher matcher = pattern.matcher(text);
        boolean whole = matcher.matches();
        matcher = pattern.matcher(text);
        boolean start = matcher.lookingAt();
        matcher = pattern.matcher(text);
        boolean anywhere = matcher.find();
        return "matches " + whole + ", lookingAt " + start + ", find " + anywhere + " at " + matcher.start() + " giving " + matcher.group();
    }

    public static List<String> findAll(String regex, String text) {
        List<String> found = new ArrayList<>();
        Matcher matcher = Pattern.compile(regex).matcher(text);
        while (matcher.find()) {
            found.add(matcher.group());
        }
        return found;
    }

    public static String characterClasses() {
        String text = "Ab1 _x-9 é!";
        return "digits " + findAll("\\d", text) + ", word characters " + findAll("\\w+", text) + ", letters only " + findAll("[A-Za-z]+", text)
                + ", not a space " + findAll("\\S+", text);
    }

    public static String anchors() {
        return "contains cat " + "concatenate".matches(".*cat.*") + ", String.matches needs the whole string: " + "concatenate".matches("cat")
                + ", word boundary finds " + findAll("\\bcat\\b", "cat concat cat.") + ", starts-with " + Pattern.compile("^cat").matcher("cat food").find();
    }

    public static String greedyVersusLazy() {
        String html = "<b>bold</b> and <i>italic</i>";
        return "greedy " + findAll("<.+>", html) + ", lazy " + findAll("<.+?>", html);
    }

    public static String quantifiers() {
        return "a? " + findAll("ab?", "a ab abb") + ", a* " + findAll("ab*", "a ab abb") + ", a+ " + findAll("ab+", "a ab abb")
                + ", a{2} " + findAll("a{2}", "a aa aaa") + ", a{2,3} " + findAll("a{2,3}", "a aa aaaa");
    }

    public static String alternationAndFlags() {
        Pattern ci = Pattern.compile("java|kotlin", Pattern.CASE_INSENSITIVE);
        Pattern multiline = Pattern.compile("^\\w+", Pattern.MULTILINE);
        List<String> found = new ArrayList<>();
        Matcher matcher = multiline.matcher("first line\nsecond line");
        while (matcher.find()) {
            found.add(matcher.group());
        }
        return "case-insensitive finds " + findAll(ci.pattern(), "Java KOTLIN scala").size() + " without the flag, with the flag "
                + ci.matcher("Java KOTLIN scala").results().count() + ", multiline line starts " + found;
    }
}
