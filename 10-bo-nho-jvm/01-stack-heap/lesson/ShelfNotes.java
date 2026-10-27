import java.util.Arrays;

public class ShelfNotes {
    static void bump(int cups) {
        // Chỉ tăng bản sao nên main không thấy
        cups = cups + 1;
    }

    static void fill(int[] trays) {
        // Ghi qua phiếu vào mảng chung
        trays[0] = 7;
    }

    static int total(int n) {
        // Điều kiện dừng của đệ quy
        if (n == 0) {
            return 0;
        }
        return n + total(n - 1);
    }

    public static void main(String[] args) {
        int cups = 5;
        int[] trays = {1, 2, 3};
        bump(cups);
        System.out.println("step 1: " + cups);
        fill(trays);
        System.out.println("step 2: " + Arrays.toString(trays));
        // Chỉ chép phiếu, vẫn một mảng
        int[] same = trays;
        same[1] = 8;
        System.out.println("step 3: " + Arrays.toString(trays) + " " + (trays == same));
        // Mảng mới trên heap, độc lập với trays
        int[] copy = Arrays.copyOf(trays, trays.length);
        copy[2] = 9;
        System.out.println("step 4: " + Arrays.toString(trays) + " " + Arrays.toString(copy));
        System.out.println("step 5: " + total(4));
    }
}
