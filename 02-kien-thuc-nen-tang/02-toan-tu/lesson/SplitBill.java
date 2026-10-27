public class SplitBill {
    public static void main(String[] args) {
        int total = 1_350_000;
        int people = 7;
        int each = total / people; // chia nguyên: phần mỗi người
        int left = total % people; // phần dư là số đồng chưa chia được
        System.out.println("each = " + each);
        System.out.println("left = " + left);
        System.out.println("last pays = " + (each + left)); // ngoặc để cộng số trước khi ghép chuỗi
        System.out.println("check = " + (each * people + left == total));
    }
}
