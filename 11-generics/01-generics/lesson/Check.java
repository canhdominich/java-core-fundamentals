import java.util.ArrayList;
import java.util.List;

public class Check {
    static class Box<T> {
        T content;
    }

    public static void main(String[] args) {
        Box<String> a = new Box<>();
        Box<Integer> b = new Box<>();
        System.out.println(a.getClass() == b.getClass());
        List<String> x = new ArrayList<>();
        List<Integer> y = new ArrayList<>();
        System.out.println(x.getClass() == y.getClass());
        Integer small1 = 127;
        Integer small2 = 127;
        Integer big1 = 128;
        Integer big2 = 128;
        System.out.println((small1 == small2) + " " + (big1 == big2) + " " + big1.equals(big2));
        Integer nothing = null;
        try {
            int boom = nothing;
            System.out.println(boom);
        } catch (NullPointerException e) {
            System.out.println("unboxing null: NullPointerException");
        }
    }
}
