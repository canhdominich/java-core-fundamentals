abstract class Plain {
    void hello() { System.out.println("hello"); }
}

class Use {
    Plain make() {
        return new Plain();
    }
}
