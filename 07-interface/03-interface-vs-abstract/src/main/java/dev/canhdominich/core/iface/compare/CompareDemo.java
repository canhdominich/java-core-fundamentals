package dev.canhdominich.core.iface.compare;

public class CompareDemo {

    public static String skeletalClass() {
        MapStorage storage = new MapStorage();
        storage.put("a", "1");
        storage.put("b", "2");
        StringBuilder out = new StringBuilder("size " + storage.size() + ", writes " + storage.writes() + ", isEmpty " + storage.isEmpty());
        try {
            storage.put(" ", "x");
        } catch (IllegalArgumentException e) {
            out.append(", blank key refused: ").append(e.getMessage()).append(", writes still ").append(storage.writes());
        }
        return out.toString();
    }

    public static String decoratorsOnlyNeedTheInterface() {
        MapStorage real = new MapStorage();
        AuditedStorage audited = new AuditedStorage(real);
        audited.put("k", "v");
        String value = audited.get("k");
        Storage readOnly = new ReadOnlyStorage(audited);
        String attempt;
        try {
            readOnly.put("x", "y");
            attempt = "written";
        } catch (UnsupportedOperationException e) {
            attempt = e.getMessage();
        }
        return "value " + value + ", audit " + audited.auditLog() + ", read-only wrapper: " + attempt + ", real storage size " + real.size();
    }

    public static String oneParentManyInterfaces() {
        Object any = new AuditedStorage(new MapStorage());
        return "AuditedStorage is Storage " + (any instanceof Storage) + ", Auditable " + (any instanceof Auditable)
                + ", AbstractStorage " + (any instanceof AbstractStorage) + "; MapStorage is AbstractStorage "
                + ((Object) new MapStorage() instanceof AbstractStorage) + ", Auditable " + ((Object) new MapStorage() instanceof Auditable);
    }

    public static void main(String[] args) {
        System.out.println("skeletal class      = " + skeletalClass());
        System.out.println("decorators          = " + decoratorsOnlyNeedTheInterface());
        System.out.println("types               = " + oneParentManyInterfaces());
        String stateInInterface = "interface I { int count = 0; } class A implements I { void inc() { count++; } }";
        String twoParents = "abstract class A { } abstract class B { } class C extends A, B { }";
        String evolveAbstract = "interface Storage2 { void put(String k); void remove(String k); } class Old implements Storage2 { public void put(String k) { } }";
        String evolveDefault = "interface Storage2 { void put(String k); default void remove(String k) { } } class Old implements Storage2 { public void put(String k) { } }";
        String interfaceConstructor = "interface I { I() { } }";
        String protectedInInterface = "interface I { protected void f(); }";
        System.out.println("state in an interface     = " + Javac.messages("A", stateInInterface));
        System.out.println("two abstract parents      = " + Javac.messages("C", twoParents));
        System.out.println("add an abstract method    = " + Javac.messages("Old", evolveAbstract));
        System.out.println("add a default method      = " + Javac.messages("Old", evolveDefault));
        System.out.println("constructor in interface  = " + Javac.messages("I", interfaceConstructor));
        System.out.println("protected in interface    = " + Javac.messages("I", protectedInInterface));
    }
}
