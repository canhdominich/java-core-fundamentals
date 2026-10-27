public class Selfcheck {
    static int half(int n) {
        return n / 2; // chia số nguyên cắt phần thập phân
    }

    static void show(int n) {
        System.out.println(n);
    }

    static int sign(int x) {
        if (x > 0) {
            return 1;
        } else if (x < 0) {
            return -1;
        }
        return 0; // trường hợp x bằng 0 mà bản lỗi bỏ sót
    }

    public static void main(String[] args) {
        int a = half(9);
        show(a);
        show(half(a) + 1);
        System.out.println(sign(7) + " " + sign(-3) + " " + sign(0));
    }
}
