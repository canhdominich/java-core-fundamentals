package dev.canhdominich.core.oop.constructors;

public class Order {

    private final String id;
    private final int quantity;
    private final double price;

    public Order(String id) {
        this(id, 1);
        Trace.log("Order(id) body");
    }

    public Order(String id, int quantity) {
        this(id, quantity, 9.99);
        Trace.log("Order(id, quantity) body");
    }

    public Order(String id, int quantity, double price) {
        Trace.log("Order(id, quantity, price) body");
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }

    public String summary() {
        return id + " x" + quantity + " at " + price;
    }
}
