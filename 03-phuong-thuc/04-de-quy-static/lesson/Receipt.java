public class Receipt {
    static int issued; // bảng đếm chung cho mọi hoá đơn
    int number;
    String name;

    Receipt(String name) {
        this.name = name;
        number = ++issued; // tăng bảng đếm rồi lấy làm số hoá đơn của đối tượng này
    }

    String print() {
        return "Receipt #" + number + " for " + name;
    }

    public static void main(String[] args) {
        Receipt a = new Receipt("Latte");
        Receipt b = new Receipt("Tea");
        Receipt c = new Receipt("Cake");
        System.out.println(a.print());
        System.out.println(b.print());
        System.out.println(c.print());
        System.out.println("issued: " + Receipt.issued);
    }
}
