import java.util.Arrays;

public class TrayBoard {
    static int[] withExtra(int[] old, int extra) {
        // Mảng có độ dài cố định nên phải tạo mảng mới
        int[] bigger = Arrays.copyOf(old, old.length + 1);
        bigger[old.length] = extra;
        return bigger;
    }

    static void clearAll(int[] trays) {
        // Sửa qua phiếu nên mảng của nơi gọi đổi theo
        Arrays.fill(trays, 0);
    }

    public static void main(String[] args) {
        int[] trays = {4, 5, 6};
        int[] more = withExtra(trays, 7);
        System.out.println(Arrays.toString(trays) + " " + Arrays.toString(more));
        clearAll(trays);
        System.out.println(Arrays.toString(trays) + " " + Arrays.toString(more));
        System.out.println(trays == more);
    }
}
