public class Bill {
    static long withVat(long net) {
        return Math.round(net * 1.1); // nhân 1.1 rồi làm tròn tới số nguyên gần nhất
    }

    static void printLine(String item, long net) {
        long gross = withVat(net); // lưu giá trị trả về, không lưu thì mất
        System.out.println(item + ": " + gross);
    }

    public static void main(String[] args) {
        printLine("Latte", 45000);
        printLine("Tea", 5);
        long net = 5;
        withVat(net); // cố ý không gán kết quả nên net không đổi
        System.out.println("net is still " + net);
    }
}
