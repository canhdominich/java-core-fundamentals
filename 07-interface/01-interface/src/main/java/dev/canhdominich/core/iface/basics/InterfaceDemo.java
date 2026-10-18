package dev.canhdominich.core.iface.basics;

import java.util.ArrayList;
import java.util.List;

public class InterfaceDemo {

    public static String oneRunnerManyKinds() {
        List<Payable> items = List.of(new Salary("An", 200_000, 5_000), new SupplierInvoice("Acme", 800_000, 10),
                new SupplierInvoice("Big Co", 2_000_000, 10));
        return "total " + PaymentRun.total(items) + ", over the limit: " + PaymentRun.overLimit(items);
    }

    public static String severalInterfaces() {
        Salary salary = new Salary("An", 1000, 200);
        Payable asPayable = salary;
        Exportable asExportable = salary;
        Object any = salary;
        return "as Payable: " + asPayable.payee() + " " + asPayable.amount() + ", as Exportable: " + asExportable.toCsv()
                + ", instanceof Payable " + (any instanceof Payable) + ", instanceof Exportable " + (any instanceof Exportable)
                + ", a SupplierInvoice is Exportable " + ((Object) new SupplierInvoice("x", 1, 0) instanceof Exportable);
    }

    public static String substitutableClock() {
        Payable payable = new SupplierInvoice("Acme", 100, 10);
        String real = new Receipt(new SystemClock()).issue(payable);
        String fixed = new Receipt(new FixedClock(1_700_000_000_000L)).issue(payable);
        return "fixed clock gives a stable receipt: " + fixed + "; real clock starts with the same words: " + real.startsWith("paid 110 to Acme at ");
    }

    public static String anonymousImplementation() {
        Payable tip = new Payable() {
            @Override
            public String payee() {
                return "waiter";
            }

            @Override
            public long amount() {
                return 15;
            }
        };
        return tip.payee() + " " + tip.amount() + " within limit " + tip.isWithinLimit();
    }

    public static List<String> constantsAreStatic() {
        List<String> out = new ArrayList<>();
        out.add("Payable.MAX_AMOUNT " + Payable.MAX_AMOUNT);
        out.add("same value through an implementing class " + Salary.MAX_AMOUNT);
        return out;
    }

    public static void main(String[] args) {
        System.out.println("one runner, many kinds = " + oneRunnerManyKinds());
        System.out.println("several interfaces     = " + severalInterfaces());
        System.out.println("substitutable clock    = " + substitutableClock());
        System.out.println("anonymous class        = " + anonymousImplementation());
        System.out.println("constants              = " + constantsAreStatic());
        String instantiate = "interface I { void f(); } class A { I i = new I(); }";
        String weaker = "interface I { void f(); } class A implements I { void f() { } }";
        String missing = "interface I { void f(); void g(); } class A implements I { public void f() { } }";
        String assignConstant = "interface I { int X = 1; } class A { void f() { I.X = 2; } }";
        String interfaceBody = "interface I { void f() { } }";
        String extendsInterface = "interface I { } class A extends I { }";
        String implementsClass = "class B { } class A implements B { }";
        String privateMember = "interface I { private void f(); }";
        System.out.println("new on an interface      = " + Javac.messages("A", instantiate));
        System.out.println("weaker access            = " + Javac.messages("A", weaker));
        System.out.println("missing method           = " + Javac.messages("A", missing));
        System.out.println("assign an interface field= " + Javac.messages("A", assignConstant));
        System.out.println("method with a body       = " + Javac.messages("I", interfaceBody));
        System.out.println("class extends interface  = " + Javac.messages("A", extendsInterface));
        System.out.println("class implements class   = " + Javac.messages("A", implementsClass));
        System.out.println("private abstract method  = " + Javac.messages("I", privateMember));
    }
}
