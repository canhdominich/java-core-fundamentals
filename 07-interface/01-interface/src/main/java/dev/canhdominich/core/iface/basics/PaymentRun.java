package dev.canhdominich.core.iface.basics;

import java.util.List;

public final class PaymentRun {

    private PaymentRun() {
    }

    public static long total(List<Payable> items) {
        long sum = 0;
        for (Payable item : items) {
            sum += item.amount();
        }
        return sum;
    }

    public static String overLimit(List<Payable> items) {
        StringBuilder out = new StringBuilder();
        for (Payable item : items) {
            if (!item.isWithinLimit()) {
                out.append(item.payee()).append(' ');
            }
        }
        return out.toString().trim();
    }
}
