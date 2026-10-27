public class Breakages {
    public static void main(String[] args) {
        String typed = new String("an");
        System.out.println("typed == literal " + (typed == "an") + ", equals " + typed.equals("an"));
        String s = "cat";
        String t = s;
        s += "s";
        System.out.println("t after s += s: " + t + ", s " + s);
        System.out.println("Aa/BB same hash " + ("Aa".hashCode() == "BB".hashCode()) + ", equals " + "Aa".equals("BB"));
        System.out.println("ab hash " + "ab".hashCode() + ", by formula " + ('a' * 31 + 'b'));
        System.out.println("b2 " + ("ja" + "va" == "java"));
        String p = "ja";
        System.out.println("b2 " + (p + "va" == "java"));
        final String q = "ja";
        System.out.println("b2 " + (q + "va" == "java"));
        String a = "go";
        String b = "go";
        String c = new String("go");
        System.out.println("q2 " + (a == b) + " " + (a == c) + " " + (a == c.intern()));
        String x = "cat";
        String y = x;
        x = x + "s";
        y = y.toUpperCase();
        System.out.println("q3 " + x + " " + y);
        String hi1 = "hi";
        String hi2 = "hi";
        System.out.println("cc1 " + (hi1 == hi2));
        String e = "ja" + new String("va");
        System.out.println("cc3 " + (e == "java"));
        String one = "x";
        String two = one;
        one = one + "y";
        String three = new String("x");
        String four = three.intern();
        System.out.println("b1 a==b " + (one == two) + ", b==d " + (two == four) + ", b==c " + (two == three) + ", b.equals(c) " + two.equals(three));
    }
}
