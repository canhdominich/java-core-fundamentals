public class KitchenCall {
    static void change(int cups, int[] trays) {
        // Chỉ sửa bản sao nằm trong khung của change
        cups = 99;
        // Đi theo phiếu tới mảng thật nên main cũng thấy thay đổi
        trays[0] = 99;
    }

    public static void main(String[] args) {
        int cups = 5;
        int[] trays = {1, 2, 3};
        // Chép giá trị 5 và chép phiếu tới mảng
        change(cups, trays);
        System.out.println(cups);
        System.out.println(trays[0]);
    }
}
