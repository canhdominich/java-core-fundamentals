import java.util.Arrays;

public class Deep {
    static void zeroOut(int... values) {
        Arrays.fill(values, 0); // sửa thẳng mảng của người gọi
    }

    static String pick(int a, int b) {
        return "two ints";
    }

    static String pick(int... values) {
        return "varargs of " + values.length;
    }

    static boolean sameObject(Object parameter, Object original) {
        return parameter == original; // hai bản sao của cùng một địa chỉ
    }

    static void sortInPlace(int[] data) {
        Arrays.sort(data);
    }

    static int[] sortedCopy(int[] data) {
        int[] copy = Arrays.copyOf(data, data.length);
        Arrays.sort(copy);
        return copy;
    }

    public static void main(String[] args) {
        int[] original = {1, 2, 3};
        zeroOut(original);
        System.out.println("zeroOut(original) -> " + Arrays.toString(original));
        System.out.println("pick(1, 2)    = " + pick(1, 2));
        System.out.println("pick(1, 2, 3) = " + pick(1, 2, 3));
        System.out.println("pick()        = " + pick());
        Object o = new Object();
        System.out.println("same object inside the method: " + sameObject(o, o));
        int[] data = {3, 1, 2};
        int[] sorted = sortedCopy(data);
        System.out.println("sortedCopy: data " + Arrays.toString(data) + ", result " + Arrays.toString(sorted));
        sortInPlace(data);
        System.out.println("sortInPlace: data " + Arrays.toString(data));
    }
}
