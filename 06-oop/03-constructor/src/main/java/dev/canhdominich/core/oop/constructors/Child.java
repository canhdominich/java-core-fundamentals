package dev.canhdominich.core.oop.constructors;

public class Child extends Parent {

    int childField = Trace.log("Child field initializer");
    String label = "ready";
    String seenByParent;

    {
        Trace.log("Child initializer block");
    }

    public Child() {
        Trace.log("Child constructor sees label=" + label);
    }

    @Override
    void describe() {
        seenByParent = "label=" + label;
        Trace.log("Child.describe sees " + seenByParent);
    }
}
