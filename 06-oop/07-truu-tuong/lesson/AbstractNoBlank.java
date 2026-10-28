public class AbstractNoBlank {
    public static void main(String[] args) {
        new Impl().hello();
    }
}

abstract class Base {
    void hello() { System.out.println("hello"); }
}

class Impl extends Base {
}
