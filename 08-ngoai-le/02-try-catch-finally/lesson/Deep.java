public class Deep {
    public static void main(String[] args) {
        try (Machine a = new Machine("A"); Machine b = new Machine("B")) {
            System.out.println("use");
        }
        try {
            Integer.parseInt("x");
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println("multi " + e);
        }
        System.out.println("tricky " + tricky());
    }

    static int tricky() {
        try {
            throw new IllegalStateException("lost");
        } finally {
            // Cẩn thận: return trong finally nuốt luôn ngoại lệ đang bay
            return 7;
        }
    }
}

class Machine implements AutoCloseable {
    private final String name;

    Machine(String name) {
        this.name = name;
        System.out.println("open " + name);
    }

    public void close() {
        System.out.println("close " + name);
    }
}
