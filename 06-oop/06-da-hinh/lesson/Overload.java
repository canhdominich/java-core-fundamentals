public class Overload {
    static String show(Object o) {
        return "object";
    }

    static String show(String s) {
        return "string";
    }

    public static void main(String[] args) {
        Object x = "hi";
        String y = "hi";
        System.out.println(show(x));
        System.out.println(show(y));
        System.out.println(show((Object) y));
    }
}
