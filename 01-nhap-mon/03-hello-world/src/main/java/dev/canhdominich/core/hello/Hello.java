package dev.canhdominich.core.hello;

public class Hello {

    public static String greeting(String name) {
        return "Hello, " + name + "!";
    }

    public static void main(String[] args) {
        String name = args.length > 0 ? args[0] : "Java";
        System.out.println(greeting(name));
    }
}
