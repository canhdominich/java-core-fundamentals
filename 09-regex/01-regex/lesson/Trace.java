import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Trace {
    public static void main(String[] args) {
        Matcher m = Pattern.compile("\\d+").matcher("a1b22c333");
        while (m.find()) {
            System.out.println(m.group() + " " + m.start() + " " + m.end());
        }
        Matcher g = Pattern.compile("=(.+);").matcher("price=10;tax=2;");
        g.find();
        System.out.println(g.group(1));
        Matcher l = Pattern.compile("=(.+?);").matcher("price=10;tax=2;");
        while (l.find()) {
            System.out.println(l.group(1));
        }
        Pattern drink = Pattern.compile("[A-Z]{2}-\\d{2}");
        for (String s : new String[] {"LT-05", "lt-05", "LT-5", "LT-055"}) {
            System.out.println(s + " " + drink.matcher(s).matches());
        }
    }
}
