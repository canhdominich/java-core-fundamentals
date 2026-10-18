package dev.canhdominich.core.iface.modern;

import java.util.List;

public class ModernDemo {

    public static List<String> defaultMethodsFromTheInterface() {
        CollectingLogger logger = new CollectingLogger();
        logger.info("started");
        logger.error("failed");
        return logger.lines();
    }

    public static List<String> overridingADefault() {
        PrefixLogger logger = new PrefixLogger();
        logger.info("started");
        logger.error("failed");
        return logger.lines();
    }

    public static String staticFactory() {
        Logger silent = Logger.silent();
        silent.info("nobody hears this");
        return "silent logger created through Logger.silent(), no lines kept";
    }

    public static String conflictResolved() {
        return "Duck " + new Duck().move();
    }

    public static String classWins() {
        return "Goose " + new Goose().move();
    }

    public static String functionalInterface() {
        StringRule rule = StringRule.notBlank().and(StringRule.maxLength(5));
        StringRule inverse = rule.negate();
        return "rule on 'java' " + rule.test("java") + ", on 'javascript' " + rule.test("javascript") + ", on blank " + rule.test("  ")
                + ", negated on 'javascript' " + inverse.test("javascript");
    }

    public static void main(String[] args) {
        System.out.println("inherited defaults    = " + defaultMethodsFromTheInterface());
        System.out.println("overriding a default  = " + overridingADefault());
        System.out.println("static factory        = " + staticFactory());
        System.out.println("conflict resolved     = " + conflictResolved());
        System.out.println("class wins            = " + classWins());
        System.out.println("functional interface  = " + functionalInterface());
        String conflict = "interface A { default String f() { return \"a\"; } } interface B { default String f() { return \"b\"; } } class C implements A, B { }";
        String staticInherited = "interface I { static void s() { } } class C implements I { void g() { s(); } }";
        String staticViaInstance = "interface I { static void s() { } } class C implements I { void g() { C.s(); } }";
        String notFunctional = "@FunctionalInterface interface F { void a(); void b(); }";
        String defaultNoBody = "interface I { default void f(); }";
        String privateNoBody = "interface I { private void f(); }";
        System.out.println("two unrelated defaults   = " + Javac.messages("C", conflict));
        System.out.println("static method inherited  = " + Javac.messages("C", staticInherited));
        System.out.println("static via implementing  = " + Javac.messages("C", staticViaInstance));
        System.out.println("two abstract methods     = " + Javac.messages("F", notFunctional));
        System.out.println("default without a body   = " + Javac.messages("I", defaultNoBody));
        System.out.println("private without a body   = " + Javac.messages("I", privateNoBody));
    }
}
