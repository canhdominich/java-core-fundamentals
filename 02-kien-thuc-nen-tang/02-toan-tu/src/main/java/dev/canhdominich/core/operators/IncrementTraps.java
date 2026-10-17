package dev.canhdominich.core.operators;

public class IncrementTraps {

    public static int selfAssignment() {
        int i = 5;
        i = i++;
        return i;
    }

    public static void main(String[] args) {
        int a = 5;
        int b = a++;
        int c = ++a;
        System.out.println("a = 5; b = a++ -> b=" + b + " a=" + (a - 1) + "; c = ++a -> c=" + c + " a=" + a);
        System.out.println("i = 5; i = i++     -> i=" + selfAssignment());
        int x = 5;
        int y = x++ * 2 + x;
        System.out.println("x = 5; y = x++ * 2 + x -> y=" + y + " x=" + x);
        int j = 5;
        int k = ++j + j++;
        System.out.println("j = 5; k = ++j + j++   -> k=" + k + " j=" + j);
        int count = 0;
        for (int i = 0; i < 3; i++) {
            count += i++;
        }
        System.out.println("loop with i++ inside the body -> count=" + count);
    }
}
