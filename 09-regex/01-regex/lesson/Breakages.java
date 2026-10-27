import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Breakages {
    public static void main(String[] args) {
        System.out.println("matches on a longer text: " + "order 42 shipped".matches("\\d+"));
        System.out.println("find on the same text: " + Pattern.compile("\\d+").matcher("order 42 shipped").find());
        try {
            Pattern.compile("(abc");
        } catch (PatternSyntaxException e) {
            System.out.println("compile: " + e.getDescription() + " near index " + e.getIndex());
        }
        try {
            Matcher m = Pattern.compile("\\d+").matcher("no digits here");
            m.group();
        } catch (IllegalStateException e) {
            System.out.println("group before find: " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
        try {
            Matcher m = Pattern.compile("(\\d+)").matcher("order 42");
            m.find();
            m.group(2);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("group 2: " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }
}
