package dev.canhdominich.core.oop.abstraction;

import java.util.ArrayList;
import java.util.List;

public class AbstractionDemo {

    public static List<String> shapes() {
        List<Shape> shapes = List.of(new Circle(1), new Rectangle(3, 4));
        List<String> descriptions = new ArrayList<>();
        for (Shape shape : shapes) {
            descriptions.add(shape.describe());
        }
        return descriptions;
    }

    public static List<String> constructionOrder() {
        Shape.clearLog();
        new Rectangle(2, 5);
        return Shape.constructedLog();
    }

    public static String invalidShape() {
        try {
            new Circle(-1);
            return "created";
        } catch (IllegalArgumentException e) {
            return e.getMessage() + "; abstract part already built: " + Shape.constructedLog().contains("Shape(circle)");
        }
    }

    public static String csv() {
        return new CsvExporter().export(List.of("alpha, beta", "gamma"));
    }

    public static String html() {
        return new HtmlExporter().export(List.of("a < b", "c"));
    }

    public static String emptyExport() {
        try {
            return new CsvExporter().export(List.of());
        } catch (IllegalArgumentException e) {
            return "refused: " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        System.out.println("shapes             = " + shapes());
        System.out.println("construction order = " + constructionOrder());
        System.out.println("invalid shape      = " + invalidShape());
        System.out.println("csv export:\n" + csv());
        System.out.println("html export:\n" + html());
        System.out.println("empty export       = " + emptyExport());
        String instantiate = "abstract class A { abstract void f(); } class B { A a = new A(); }";
        String missing = "abstract class A { abstract void f(); } class B extends A { }";
        String abstractBody = "abstract class A { abstract void f() { } }";
        String abstractInConcrete = "class A { abstract void f(); }";
        String abstractStatic = "abstract class A { abstract static void f(); }";
        String abstractPrivate = "abstract class A { private abstract void f(); }";
        String abstractFinal = "abstract class A { abstract final void f(); }";
        String overrideFinal = "abstract class A { final void run() { } } class B extends A { void run() { } }";
        System.out.println("new on an abstract class    = " + Javac.messages("B", instantiate));
        System.out.println("abstract method not covered = " + Javac.messages("B", missing));
        System.out.println("abstract method with a body = " + Javac.messages("A", abstractBody));
        System.out.println("abstract in a concrete class= " + Javac.messages("A", abstractInConcrete));
        System.out.println("abstract and static         = " + Javac.messages("A", abstractStatic));
        System.out.println("abstract and private        = " + Javac.messages("A", abstractPrivate));
        System.out.println("abstract and final          = " + Javac.messages("A", abstractFinal));
        System.out.println("override the final template = " + Javac.messages("B", overrideFinal));
    }
}
