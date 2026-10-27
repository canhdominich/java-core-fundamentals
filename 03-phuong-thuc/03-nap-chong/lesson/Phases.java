public class Phases {
    static String pick(long x) {
        return "long";
    }

    static String pick(Integer x) {
        return "Integer";
    }

    static String pick(Object x) {
        return "Object";
    }

    static String pick(int... x) {
        return "int...";
    }

    public static void main(String[] args) {
        System.out.println("pick(5)                = " + pick(5)); // pha 1: int mở rộng thành long
        System.out.println("pick('a')              = " + pick('a'));
        System.out.println("pick(Integer.valueOf(5)) = " + pick(Integer.valueOf(5)));
        System.out.println("pick(5.0)              = " + pick(5.0)); // pha 2: đóng hộp thành Double rồi coi như Object
        System.out.println("pick()                 = " + pick()); // pha 3: chỉ varargs nhận được
        System.out.println("pick(1, 2)             = " + pick(1, 2));
    }
}
