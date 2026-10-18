package dev.canhdominich.core.iface.basics;

public class Salary implements Payable, Exportable {

    private final String employee;
    private final long base;
    private final long bonus;

    public Salary(String employee, long base, long bonus) {
        this.employee = employee;
        this.base = base;
        this.bonus = bonus;
    }

    @Override
    public String payee() {
        return employee;
    }

    @Override
    public long amount() {
        return base + bonus;
    }

    @Override
    public String toCsv() {
        return employee + "," + base + "," + bonus;
    }
}
