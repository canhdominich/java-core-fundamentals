public class Ctor {
    public static void main(String[] args) {
        new Child();
    }
}

abstract class Parent {
    Parent() {
        System.out.println("Parent constructor");
    }

    abstract void work();
}

class Child extends Parent {
    Child() {
        System.out.println("Child constructor");
    }

    @Override
    void work() {
    }
}
