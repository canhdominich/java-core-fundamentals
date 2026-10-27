public class Selfcheck {
    static String f(int x) {
        return "int";
    }

    static String f(long x) {
        return "long";
    }

    static String f(double x) {
        return "double";
    }

    static String f(Object x) {
        return "Object";
    }

    static String show(Object value) {
        return "Object";
    }

    static String show(String value) {
        return "String";
    }

    static String pick(String s) {
        return "String";
    }

    static String pick(StringBuilder b) {
        return "StringBuilder";
    }

    public static void main(String[] args) {
        System.out.println(f(5) + " " + f(5L) + " " + f(5.0f) + " " + f('x') + " " + f(Integer.valueOf(5))); // Integer chỉ nâng lên Object ở pha 1, chưa được mở hộp
        Object o = "hi";
        String s = "hi";
        System.out.println(show(o) + " " + show(s));
        o = s; // đổi đối tượng bên trong, nhãn của o vẫn là Object
        System.out.println(show(o));
        System.out.println(pick((String) null));
    }
}
