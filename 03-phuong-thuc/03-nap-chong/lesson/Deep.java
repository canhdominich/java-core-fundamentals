import java.util.ArrayList;
import java.util.List;

public class Deep {
    static String boxing(Integer x) {
        return "Integer";
    }

    static String boxing(Object x) {
        return "Object";
    }

    static String boxing(int... x) {
        return "int...";
    }

    public static void main(String[] args) {
        System.out.println("boxing(5)    = " + boxing(5));
        System.out.println("boxing(5L)   = " + boxing(5L)); // Long không phải Integer nhưng là Object
        System.out.println("boxing(5, 6) = " + boxing(5, 6));
        System.out.println("boxing()     = " + boxing());
        System.out.println("boxing(\"a\") = " + boxing("a"));
        List<Integer> list = new ArrayList<>(List.of(10, 20, 30, 1));
        list.remove(1); // int chọn remove theo chỉ số
        System.out.println("remove(1)                = " + list);
        list = new ArrayList<>(List.of(10, 20, 30, 1));
        list.remove(Integer.valueOf(1)); // Integer chọn remove theo giá trị
        System.out.println("remove(Integer.valueOf(1)) = " + list);
    }
}
