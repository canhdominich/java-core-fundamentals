class Invoice {
    private static int nextNumber = 1000;
    private final int number;

    Invoice() {
        this.number = nextNumber++;
    }

    void reset() {
        number = 0;
    }
}
