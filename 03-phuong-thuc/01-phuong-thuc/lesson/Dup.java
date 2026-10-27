public class Dup {
    static long cartCopy(long net) {
        return net * 110 / 100; // bản sao thứ nhất: phép chia số nguyên cắt phần thập phân
    }

    static long invoiceCopy(long net) {
        return Math.round(net * 1.1); // bản sao thứ hai: làm tròn, nên lệch với bản đầu
    }

    public static void main(String[] args) {
        for (long net : new long[] {5, 15, 100}) {
            System.out.println("net " + net + ": cart " + cartCopy(net) + ", invoice " + invoiceCopy(net));
        }
    }
}
