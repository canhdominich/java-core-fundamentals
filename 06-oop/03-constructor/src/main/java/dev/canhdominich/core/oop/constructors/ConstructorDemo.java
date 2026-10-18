package dev.canhdominich.core.oop.constructors;

import java.util.List;

public class ConstructorDemo {

    public static String shadowing() {
        BuggyPerson bug = new BuggyPerson("An", 30);
        Person good = new Person("An", 30);
        return "buggy: name " + bug.name + ", age " + bug.age + " | fixed: name " + good.name() + ", age " + good.age();
    }

    public static String validation() {
        StringBuilder out = new StringBuilder();
        for (Object[] args : new Object[][] {{"", 20}, {"An", -1}, {"An", 200}}) {
            try {
                new Person((String) args[0], (Integer) args[1]);
            } catch (IllegalArgumentException e) {
                out.append(e.getMessage()).append("; ");
            }
        }
        Person chained = new Person("Binh");
        Person copied = new Person(chained);
        return out + "defaults: " + chained.name() + " " + chained.age() + ", copy: " + copied.name() + " " + copied.age();
    }

    public static List<String> chaining() {
        Trace.clear();
        Order order = new Order("A1");
        Trace.log(order.summary());
        return Trace.steps();
    }

    public static List<String> initialisationOrder() {
        Trace.clear();
        new Child();
        return Trace.steps();
    }

    public static String parentSeesDefaults() {
        Child child = new Child();
        return "seen by the parent constructor: " + child.seenByParent + ", label afterwards: " + child.label;
    }

    public static String privateConstructor() {
        return "of(0) == ZERO " + (Money.of(0) == Money.ZERO) + ", of(5) == of(5) " + (Money.of(5) == Money.of(5))
                + ", of(5).amount " + Money.of(5).amount();
    }

    public static String defensiveCopy() {
        int[] points = {1, 2, 3};
        Polygon sharing = new Polygon(points, false);
        Polygon copying = new Polygon(points, true);
        points[0] = 99;
        return "after changing the caller array: sharing " + sharing.firstX() + ", copying " + copying.firstX();
    }

    public static void main(String[] args) {
        System.out.println("shadowing        = " + shadowing());
        System.out.println("validation       = " + validation());
        System.out.println("chaining         = " + chaining());
        System.out.println("init order       = " + initialisationOrder());
        System.out.println("parent trap      = " + parentSeesDefaults());
        System.out.println("private ctor     = " + privateConstructor());
        System.out.println("defensive copy   = " + defensiveCopy());
    }
}
