package dev.canhdominich.core.oop.classes;

import java.util.Arrays;

public class ClassObjectDemo {

    public static String defaults() {
        Student student = new Student();
        return "name " + student.name + ", score " + student.score + ", passed " + student.passed + ", average " + student.average + ", marks " + student.marks;
    }

    public static String independentObjects() {
        Student an = new Student("An", 40);
        Student binh = new Student("Binh", 40);
        an.improve(15);
        return an.describe() + " | " + binh.describe();
    }

    public static String twoNamesForOneObject() {
        Student a = new Student("An", 40);
        Student b = a;
        b.improve(20);
        Student c = new Student("An", 40);
        return "a " + a.describe() + ", a == b " + (a == b) + ", a == c " + (a == c) + ", a.equals(c) " + a.equals(c);
    }

    public static String defaultToString() {
        String text = new Student("An", 40).toString();
        return "starts with Student@ " + text.startsWith("dev.canhdominich.core.oop.classes.Student@");
    }

    public static String nullReference() {
        Student missing = null;
        try {
            return missing.describe();
        } catch (NullPointerException e) {
            return "NullPointerException: " + e.getMessage();
        }
    }

    public static String arrayOfObjects() {
        Student[] students = new Student[3];
        String before = Arrays.toString(students);
        for (int i = 0; i < students.length; i++) {
            students[i] = new Student("S" + (i + 1), 10 * i);
        }
        return "before " + before + ", after " + students[2].describe();
    }

    public static String instanceCounter() {
        Student.resetCount();
        new Student();
        new Student("An", 1);
        Student kept = new Student("Binh", 2);
        return "created " + Student.created() + ", kept " + kept.describe();
    }

    static class Probe {

        int plain;
        int initialised = 7;
        String seenFirst;

        Probe() {
            seenFirst = plain + " and " + initialised;
            plain = 5;
        }
    }

    public static String fieldsBeforeTheConstructorBody() {
        Probe probe = new Probe();
        return "inside the constructor, first line: " + probe.seenFirst + "; afterwards plain " + probe.plain;
    }

    public static void main(String[] args) {
        System.out.println("defaults        = " + defaults());
        System.out.println("independent     = " + independentObjects());
        System.out.println("two names       = " + twoNamesForOneObject());
        System.out.println("default toString= " + defaultToString());
        System.out.println("null reference  = " + nullReference());
        System.out.println("array of objects= " + arrayOfObjects());
        System.out.println("instance counter= " + instanceCounter());
        System.out.println("before the body = " + fieldsBeforeTheConstructorBody());
        String classCallsInstance = "class A { void greet() { } static void f() { A.greet(); } }";
        String missingNew = "class A { void f() { String s = String(); } }";
        String unknownField = "class Student { String name; } class A { void f(Student s) { s.nickname = \"x\"; } }";
        String wrongArgument = "class Student { Student(String name) { } } class A { Student f() { return new Student(); } }";
        String typeOnLeft = "class Student { } class A { void f() { Student s = new Student; } }";
        System.out.println("instance method via class = " + Javac.messages("A", classCallsInstance));
        System.out.println("missing new               = " + Javac.messages("A", missingNew));
        System.out.println("unknown field             = " + Javac.messages("A", unknownField));
        System.out.println("no matching constructor   = " + Javac.messages("A", wrongArgument));
        System.out.println("parentheses forgotten     = " + Javac.messages("A", typeOnLeft));
    }
}
