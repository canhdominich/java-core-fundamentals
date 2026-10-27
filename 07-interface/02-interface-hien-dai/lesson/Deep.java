public class Deep {
    public static void main(String[] args) {
        Grape g = new Grape();
        System.out.println(g.label());
        Wine w = new Wine();
        System.out.println(w.label());
    }
}

interface Fruit {
    // Phương thức của lớp thắng default của interface
    default String label() { return "fruit"; }
}

class Grape implements Fruit {
    public String label() { return "grape"; }
}

class Base {
    public String label() { return "base"; }
}

// label() kế thừa từ Base cũng thắng default của Fruit
class Wine extends Base implements Fruit {
}
