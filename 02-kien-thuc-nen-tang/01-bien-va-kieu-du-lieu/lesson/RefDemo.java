public class RefDemo {
    public static void main(String[] args) {
        int[] stock = {10};
        int[] alias = stock; // chỉ sao chép tờ phiếu, vẫn một mảng
        alias[0] = 99; // sửa qua alias thì stock cũng thấy
        System.out.println("stock[0] = " + stock[0]);
        int[] other = {99};
        System.out.println("stock == alias: " + (stock == alias));
        System.out.println("stock == other: " + (stock == other)); // cùng dữ liệu nhưng khác mảng
        String a = new String("java");
        String b = new String("java");
        System.out.println("a == b: " + (a == b) + ", a.equals(b): " + a.equals(b)); // == so phiếu, equals so nội dung
    }
}
