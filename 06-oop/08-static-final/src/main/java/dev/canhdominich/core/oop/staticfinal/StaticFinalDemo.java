package dev.canhdominich.core.oop.staticfinal;

public class StaticFinalDemo {

    public static String lazyInitialisation(String mode) {
        try {
            String java = System.getProperty("java.home") + "/bin/java";
            Process process = new ProcessBuilder(java, "-classpath", System.getProperty("java.class.path"),
                    "dev.canhdominich.core.oop.staticfinal.LazyProbe", mode).redirectErrorStream(true).start();
            String output = new String(process.getInputStream().readAllBytes()).trim();
            process.waitFor();
            return output;
        } catch (Exception e) {
            return "could not run the probe: " + e;
        }
    }

    public static String constantsAreNotDeepFinal() {
        Config.HOSTS.add("c.example");
        Config.PORTS[0] = 8080;
        return "hosts " + Config.HOSTS + ", ports " + Config.PORTS[0] + " and " + Config.PORTS[1]
                + ", but the references still point to the same objects";
    }

    public static String finalFieldsAndNumbering() {
        Invoice.resetNumbering(1000);
        Invoice first = new Invoice("An", 500);
        Invoice second = new Invoice("Binh", 700);
        return "numbers " + first.number() + ", " + second.number() + ", next customer " + second.customer() + ", amount " + second.amount();
    }

    public static String finalLocalAndReference() {
        final StringBuilder log = new StringBuilder("start");
        log.append(" then changed");
        final int fixed = 10;
        return log + ", fixed " + fixed;
    }

    public static void main(String[] args) {
        System.out.println("first use is a method   = " + lazyInitialisation("method"));
        System.out.println("first use is a field    = " + lazyInitialisation("computed"));
        System.out.println("constants, not deep   = " + constantsAreNotDeepFinal());
        System.out.println("final fields          = " + finalFieldsAndNumbering());
        System.out.println("final local           = " + finalLocalAndReference());
        String reassignLocal = "class A { void f() { final int x = 1; x = 2; } }";
        String reassignParameter = "class A { void f(final int x) { x = 2; } }";
        String reassignField = "class A { final int x = 1; void f() { x = 2; } }";
        String reassignReference = "class A { void f() { final StringBuilder b = new StringBuilder(); b = new StringBuilder(); } }";
        String staticNotAssigned = "class A { static final int X; }";
        String instanceNotAssigned = "class A { final int x; }";
        String finalOverride = "class A { final void f() { } } class B extends A { void f() { } }";
        System.out.println("assign a final local      = " + Javac.messages("A", reassignLocal));
        System.out.println("assign a final parameter  = " + Javac.messages("A", reassignParameter));
        System.out.println("assign a final field      = " + Javac.messages("A", reassignField));
        System.out.println("reassign a final reference= " + Javac.messages("A", reassignReference));
        System.out.println("static final never set    = " + Javac.messages("A", staticNotAssigned));
        System.out.println("final field never set     = " + Javac.messages("A", instanceNotAssigned));
        System.out.println("override a final method   = " + Javac.messages("B", finalOverride));
    }
}
