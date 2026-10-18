package dev.canhdominich.core.methods;

public class Duplication {

    public static long cartTotalCopy(long net) {
        return net * 110 / 100;
    }

    public static long invoiceTotalCopy(long net) {
        return Math.round(net * 1.1);
    }

    public static long withVat(long net) {
        return Math.round(net * 1.1);
    }

    public static long cartTotal(long net) {
        return withVat(net);
    }

    public static long invoiceTotal(long net) {
        return withVat(net);
    }

    public static void main(String[] args) {
        for (long net : new long[] {5, 15, 100, 995}) {
            System.out.println("net " + net + ": cart copy " + cartTotalCopy(net) + ", invoice copy " + invoiceTotalCopy(net)
                    + " | one method: cart " + cartTotal(net) + ", invoice " + invoiceTotal(net));
        }
    }
}
