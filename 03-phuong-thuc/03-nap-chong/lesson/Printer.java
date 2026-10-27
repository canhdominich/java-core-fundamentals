public class Printer {
    static String show(int n) {
        return "int " + n;
    }

    static String show(double d) {
        return "double " + d;
    }

    static String show(String s) {
        return "String " + s;
    }

    static String show(int a, int b) {
        return "two ints " + (a + b);
    }

    public static void main(String[] args) {
        System.out.println(show(3));
        System.out.println(show(3.5)); // double không tự thu hẹp thành int
        System.out.println(show("hi"));
        System.out.println(show(3, 4));
        System.out.println(show('a')); // char mở rộng thành int nên in mã 97
        System.out.println(show(3L)); // long không thu hẹp thành int nên mở rộng thành double
    }
}
