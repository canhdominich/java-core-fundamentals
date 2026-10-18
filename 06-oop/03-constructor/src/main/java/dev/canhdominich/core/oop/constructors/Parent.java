package dev.canhdominich.core.oop.constructors;

public class Parent {

    int parentField = Trace.log("Parent field initializer");

    {
        Trace.log("Parent initializer block");
    }

    public Parent() {
        Trace.log("Parent constructor");
        describe();
    }

    void describe() {
        Trace.log("Parent.describe");
    }
}
