package dev.canhdominich.core.methods;

import java.util.List;

public class CallStack {

    static List<String> inner() {
        return StackWalker.getInstance().walk(frames -> frames.map(StackWalker.StackFrame::getMethodName).limit(4).toList());
    }

    static List<String> middle() {
        return inner();
    }

    static List<String> outer() {
        return middle();
    }

    public static void main(String[] args) {
        System.out.println("frames inside inner(), top first = " + outer());
    }
}
