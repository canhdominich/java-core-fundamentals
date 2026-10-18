package dev.canhdominich.core.java8.lambda;

public class LambdaDemo {

    public static void main(String[] args) {
        System.out.println("forms              = " + Syntax.forms());
        System.out.println("method references  = " + Syntax.methodReferences());
        System.out.println("composition        = " + Syntax.composition());
        System.out.println("captured variables = " + Syntax.capturingVariables());
        System.out.println("lazy evaluation    = " + Syntax.laziness());
        System.out.println("sorting            = " + Syntax.sortWithLambdas());
        ThisMeaning meaning = new ThisMeaning();
        System.out.println("this in a lambda   = " + meaning.insideLambda());
        System.out.println("this in an anonymous class = " + meaning.insideAnonymousClass());
        String modify = "import java.util.function.*; class A { void f() { int n = 1; Runnable r = () -> n++; } }";
        String modifyLater = "import java.util.function.*; class A { void f() { int n = 1; Runnable r = () -> System.out.println(n); n = 2; } }";
        String notFunctional = "class A { void f() { Object o = () -> 1; } }";
        String checked = "import java.util.function.*; class A { void f() { Supplier<String> s = () -> { throw new Exception(\"x\"); }; } }";
        String wrongArity = "import java.util.function.*; class A { void f() { Function<Integer, Integer> g = (a, b) -> a + b; } }";
        String shadow = "import java.util.function.*; class A { void f() { int n = 1; Function<Integer, Integer> g = n -> n + 1; } }";
        System.out.println("modify a captured local      = " + Javac.messages("A", modify));
        System.out.println("change it after the lambda   = " + Javac.messages("A", modifyLater));
        System.out.println("lambda for a non-interface   = " + Javac.messages("A", notFunctional));
        System.out.println("checked exception in Supplier= " + Javac.messages("A", checked));
        System.out.println("wrong number of parameters   = " + Javac.messages("A", wrongArity));
        System.out.println("parameter hides a local      = " + Javac.messages("A", shadow));
    }
}
