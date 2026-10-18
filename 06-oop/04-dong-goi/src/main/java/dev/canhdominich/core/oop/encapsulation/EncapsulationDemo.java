package dev.canhdominich.core.oop.encapsulation;

import java.util.List;

public class EncapsulationDemo {

    public static String leakyAccount() {
        LeakyAccount account = new LeakyAccount();
        account.owner = null;
        account.balance = -500;
        return "owner " + account.owner + ", balance " + account.balance + " (nothing stopped it)";
    }

    public static String safeAccount() {
        SafeAccount account = new SafeAccount("An", 100);
        StringBuilder out = new StringBuilder();
        for (long amount : new long[] {-5, 0, 500}) {
            try {
                if (amount == 500) {
                    account.withdraw(amount);
                } else {
                    account.deposit(amount);
                }
            } catch (IllegalArgumentException e) {
                out.append(e.getMessage()).append("; ");
            }
        }
        account.deposit(50);
        return out + "balance " + account.balance() + ", operations " + account.operations();
    }

    public static String leakyCourse() {
        LeakyCourse course = new LeakyCourse(2);
        course.enrol("An");
        course.enrol("Binh");
        boolean third = course.enrol("Chi");
        course.students().add("Chi");
        course.students().add("Dung");
        return "third enrolment accepted " + third + ", capacity " + course.capacity() + ", students " + course.students();
    }

    public static String safeCourse() {
        SafeCourse course = new SafeCourse(2);
        course.enrol("An");
        course.enrol("Binh");
        List<String> view = course.students();
        String attempt;
        try {
            view.add("Chi");
            attempt = "added";
        } catch (UnsupportedOperationException e) {
            attempt = "UnsupportedOperationException";
        }
        return "add to the returned list: " + attempt + ", students " + course.students();
    }

    public static String hiddenRepresentation() {
        Temperature body = Temperature.ofCelsius(36.6);
        Temperature freezing = Temperature.ofCelsius(0);
        StringBuilder out = new StringBuilder();
        out.append(String.format("celsius %.1f, fahrenheit %.1f, freezing %.1f F", body.celsius(), body.fahrenheit(), freezing.fahrenheit()));
        try {
            Temperature.ofCelsius(-300);
        } catch (IllegalArgumentException e) {
            out.append(", ").append(e.getMessage());
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println("public fields    = " + leakyAccount());
        System.out.println("private + rules  = " + safeAccount());
        System.out.println("leaky getter     = " + leakyCourse());
        System.out.println("safe getter      = " + safeCourse());
        System.out.println("hidden storage   = " + hiddenRepresentation());
        String pkg = "package other; ";
        String viaPrivate = pkg + "public class Probe { int f(dev.canhdominich.core.oop.encapsulation.Visibility v) { return v.privateLevel; } }";
        String viaPackage = pkg + "public class Probe { int f(dev.canhdominich.core.oop.encapsulation.Visibility v) { return v.packageLevel; } }";
        String viaProtected = pkg + "public class Probe { int f(dev.canhdominich.core.oop.encapsulation.Visibility v) { return v.protectedLevel; } }";
        String viaSubclass = pkg + "public class Probe extends dev.canhdominich.core.oop.encapsulation.Visibility { int f() { return protectedLevel; } }";
        String viaPublic = pkg + "public class Probe { int f(dev.canhdominich.core.oop.encapsulation.Visibility v) { return v.publicLevel; } }";
        System.out.println("private from another package          = " + Javac.messages("Probe", viaPrivate));
        System.out.println("package-private from another package  = " + Javac.messages("Probe", viaPackage));
        System.out.println("protected from an unrelated class      = " + Javac.messages("Probe", viaProtected));
        System.out.println("protected from a subclass              = " + Javac.messages("Probe", viaSubclass));
        System.out.println("public from another package            = " + Javac.messages("Probe", viaPublic));
    }
}
