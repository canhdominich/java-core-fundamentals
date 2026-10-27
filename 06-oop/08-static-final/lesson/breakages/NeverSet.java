class Invoice {
    private static int nextNumber = 1000;
    private final int number;

    Invoice() {
        this.number = nextNumber++;
    }

class Broken {
    private final int number;
}
}
