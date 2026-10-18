package dev.canhdominich.core.oop.polymorphism;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PolymorphismDemo {

    public static String strategies() {
        List<Discount> discounts = List.of(new PercentDiscount(10), new FixedDiscount(5_000), new Discount());
        return "price 100000 -> " + Checkout.total(100_000, discounts) + " after " + Checkout.names(discounts)
                + "; price 3000 -> " + Checkout.total(3_000, discounts);
    }

    public static String brokenEquals() {
        BrokenPoint a = new BrokenPoint(1, 2);
        BrokenPoint b = new BrokenPoint(1, 2);
        Object asObject = b;
        List<BrokenPoint> list = List.of(a);
        Set<BrokenPoint> set = new HashSet<>(list);
        return "a.equals(b) " + a.equals(b) + ", a.equals((Object) b) " + a.equals(asObject)
                + ", list.contains(b) " + list.contains(b) + ", set.contains(b) " + set.contains(b);
    }

    public static String goodEquals() {
        GoodPoint a = new GoodPoint(1, 2);
        GoodPoint b = new GoodPoint(1, 2);
        Object asObject = b;
        List<GoodPoint> list = List.of(a);
        Set<GoodPoint> set = new HashSet<>(list);
        return "a.equals(b) " + a.equals(b) + ", a.equals((Object) b) " + a.equals(asObject)
                + ", list.contains(b) " + list.contains(b) + ", set.contains(b) " + set.contains(b) + ", toString " + a;
    }

    public static String staticAndPrivate() {
        Base viaBase = new Derived();
        Derived viaDerived = new Derived();
        return "static via Base " + Base.who() + " and via Derived " + Derived.who()
                + ", reveal() on a Derived object " + viaBase.reveal()
                + ", Derived.secret() called directly " + viaDerived.secret()
                + ", kind() " + viaBase.kind();
    }

    public static String covariantReturn() {
        Base viaBase = new Derived();
        Base created = viaBase.create();
        Derived direct = new Derived().create();
        return "create() through Base gives a " + created.kind() + ", and on Derived the compiler knows it is Derived: " + direct.kind();
    }

    public static void main(String[] args) {
        System.out.println("strategies       = " + strategies());
        System.out.println("equals(Point)    = " + brokenEquals());
        System.out.println("equals(Object)   = " + goodEquals());
        System.out.println("static, private  = " + staticAndPrivate());
        System.out.println("covariant return = " + covariantReturn());
        String typo = "class A { void run() { } } class B extends A { @Override void rn() { } }";
        String returnType = "class A { int f() { return 1; } } class B extends A { String f() { return \"\"; } }";
        String staticOverride = "class A { void f() { } } class B extends A { static void f() { } }";
        String broaderException = "class A { void f() { } } class B extends A { void f() throws Exception { } }";
        String overloadOnly = "class A { void f(int x) { } } class B extends A { @Override void f(long x) { } }";
        System.out.println("@Override with a typo      = " + Javac.messages("B", typo));
        System.out.println("different return type      = " + Javac.messages("B", returnType));
        System.out.println("static hiding an instance  = " + Javac.messages("B", staticOverride));
        System.out.println("broader checked exception  = " + Javac.messages("B", broaderException));
        System.out.println("@Override on an overload   = " + Javac.messages("B", overloadOnly));
    }
}
