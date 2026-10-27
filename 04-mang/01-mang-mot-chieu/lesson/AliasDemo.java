import java.util.Arrays;

public class AliasDemo {
    public static void main(String[] args) {
        int[] a = {1, 2, 3};
        // Chỉ chép địa chỉ, không tạo mảng mới: a và b là hai tên của một mảng
        int[] b = a;
        // Ghi qua b, nhưng a cũng thấy vì cùng một mảng
        b[0] = 99;
        System.out.println("a " + Arrays.toString(a) + ", same " + (a == b));
        // clone tạo mảng thứ hai độc lập có cùng nội dung
        int[] c = a.clone();
        c[1] = 77;
        System.out.println("a " + Arrays.toString(a) + ", c " + Arrays.toString(c));
    }
}
