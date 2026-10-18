package dev.canhdominich.core.iface.basics;

public class SupplierInvoice implements Payable {

    private final String supplier;
    private final long net;
    private final int vatPercent;

    public SupplierInvoice(String supplier, long net, int vatPercent) {
        this.supplier = supplier;
        this.net = net;
        this.vatPercent = vatPercent;
    }

    @Override
    public String payee() {
        return supplier;
    }

    @Override
    public long amount() {
        return net + net * vatPercent / 100;
    }
}
