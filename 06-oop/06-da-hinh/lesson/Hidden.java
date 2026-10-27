public class Hidden {
    public static void main(String[] args) {
        Base b = new Derived();
        System.out.println(Base.who() + " " + Derived.who());
        System.out.println(b.reveal());
        System.out.println(b.kind());
    }
}

class Base {
    static String who() {
        return "Base.who";
    }

    private String secret() {
        return "Base.secret";
    }

    String reveal() {
        return secret();
    }

    String kind() {
        return "Base";
    }
}

class Derived extends Base {
    static String who() {
        return "Derived.who";
    }

    String secret() {
        return "Derived.secret";
    }

    @Override
    String kind() {
        return "Derived";
    }
}
