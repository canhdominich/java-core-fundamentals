package dev.canhdominich.core.types;

import java.util.List;

public class ScopeAndVar {

    static class User {
        static int count = 0;
        String name;

        User(String name) {
            this.name = name;
            count++;
        }
    }

    record Case(String label, String source) {
    }

    static final List<Case> BROKEN = List.of(
            new Case("int age; println(age)", "class A { void f() { int age; System.out.println(age); } }"),
            new Case("int age = null", "class A { void f() { int age = null; } }"),
            new Case("var age;", "class A { void f() { var age; } }"),
            new Case("var name = null;", "class A { void f() { var name = null; } }"),
            new Case("var as a field", "class A { var name = \"An\"; }"),
            new Case("var number = 10; number = \"Java\"", "class A { void f() { var number = 10; number = \"Java\"; } }"),
            new Case("long big = 3000000000;", "class A { void f() { long big = 3000000000; } }"),
            new Case("int n = 3.5;", "class A { void f() { int n = 3.5; } }"),
            new Case("use a block variable outside", "class A { void f() { { int inner = 1; } System.out.println(inner); } }"));

    public static void main(String[] args) {
        User first = new User("An");
        User second = new User("Binh");
        System.out.println("User.count after two objects = " + User.count);
        System.out.println("names: " + first.name + ", " + second.name + " (each object has its own name)");
        for (Case broken : BROKEN) {
            System.out.printf("%-34s -> %s%n", broken.label(), Javac.messages("A", broken.source()));
        }
    }
}
