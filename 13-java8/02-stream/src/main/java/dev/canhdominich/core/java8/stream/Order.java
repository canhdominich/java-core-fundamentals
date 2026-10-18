package dev.canhdominich.core.java8.stream;

public record Order(String customer, String product, int quantity, long unitPrice) {

    public long total() {
        return quantity * unitPrice;
    }
}
