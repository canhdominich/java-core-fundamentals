public class Practice {
    static int twice(int n) {
        return n * 2;
    }

    static int addOne(int n) {
        return n + 1;
    }

    static long withVat(long net) {
        return Math.round(net * 1.1);
    }

    static long lineTotal(long price, int qty) {
        if (qty <= 0) {
            return 0; // trả về sớm, không đi tới phép nhân
        }
        return price * qty;
    }

    static long printLine(String name, long price, int qty) {
        long gross = withVat(lineTotal(price, qty)); // lời gọi trong cùng được tính trước
        System.out.println(name + " x" + qty + ": " + gross);
        return gross;
    }

    static long roundToThousand(long amount) {
        return Math.round(amount / 1000.0) * 1000; // chia cho số thực để không bị cắt phần thập phân
    }

    public static void main(String[] args) {
        int a = 3;
        int b = addOne(twice(a));
        int c = twice(addOne(a));
        System.out.println("b = " + b + ", c = " + c);
        long net = 100;
        net = withVat(net);
        System.out.println("net = " + net);
        long total = printLine("Latte", 45000, 2) + printLine("Tea", 30000, 0) + printLine("Cake", 38000, 1);
        System.out.println("total: " + total);
        System.out.println(roundToThousand(49500) + " " + roundToThousand(49499) + " " + roundToThousand(6));
    }
}
