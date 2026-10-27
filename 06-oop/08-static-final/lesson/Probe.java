public class Probe {
    public static void main(String[] args) {
        System.out.println("start");
        System.out.println(Lazy.FIXED);
        Lazy.hello();
    }
}

class Lazy {
    static final int FIXED = 42;

    static {
        System.out.println("Lazy loaded");
    }

    static void hello() {
        System.out.println("hello");
    }
}
