package dev.canhdominich.core.oop.polymorphism;

import java.util.List;

public final class Checkout {

    private Checkout() {
    }

    public static long total(long price, List<Discount> discounts) {
        long current = price;
        for (Discount discount : discounts) {
            current = discount.apply(current);
        }
        return current;
    }

    public static String names(List<Discount> discounts) {
        StringBuilder out = new StringBuilder();
        for (Discount discount : discounts) {
            if (out.length() > 0) {
                out.append(", ");
            }
            out.append(discount.name());
        }
        return out.toString();
    }
}
