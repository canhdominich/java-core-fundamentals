class Invoice {
    private static int nextNumber = 1000;
    private final int number;

    Invoice() {
        this.number = nextNumber++;
    }

    static int peek() {
        return number;
    }
}
