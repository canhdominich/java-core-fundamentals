package dev.canhdominich.core.recursion;

public class StaticVsInstance {

    public static void main(String[] args) {
        Counter.reset();
        Counter a = new Counter();
        Counter b = new Counter();
        Counter c = Counter.withStart(10);
        a.increment();
        a.increment();
        b.increment();
        System.out.println("values a, b, c        = " + a.value() + ", " + b.value() + ", " + c.value());
        System.out.println("Counter.created()     = " + Counter.created());
        System.out.println("a.describe()          = " + a.describe());
        System.out.println("c.describe()          = " + c.describe());
        Counter.reset();
        System.out.println("after Counter.reset() = " + a.describe());
        String instanceFromStatic = "class A { int value; static int read() { return value; } }";
        String thisInStatic = "class A { static A self() { return this; } }";
        String callInstance = "class A { void work() { } static void run() { work(); } }";
        String staticFromInstance = "class A { static int total = 3; int read() { return total; } }";
        String redeclare = "class A { static void f() { } void f() { } }";
        System.out.println("field read from static   = " + Javac.messages("A", instanceFromStatic));
        System.out.println("this in a static method  = " + Javac.messages("A", thisInStatic));
        System.out.println("instance call from static= " + Javac.messages("A", callInstance));
        System.out.println("static read from instance= " + Javac.messages("A", staticFromInstance));
        System.out.println("same signature twice     = " + Javac.messages("A", redeclare));
    }
}
