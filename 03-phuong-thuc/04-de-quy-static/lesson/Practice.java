public class Practice {
    static int cups(int floors) {
        if (floors <= 1) { // dùng nhỏ hơn hoặc bằng để cups(0) cũng dừng
            return 1;
        }
        return floors + cups(floors - 1);
    }

    static int sumDigits(int n) {
        if (n < 10) {
            return n;
        }
        return n % 10 + sumDigits(n / 10); // chữ số cuối cộng tổng các chữ số còn lại
    }

    static long power(long base, int exponent) {
        if (exponent == 0) { // mọi số mũ 0 đều cho 1
            return 1;
        }
        return base * power(base, exponent - 1);
    }

    static long hanoiMoves(int disks) {
        if (disks == 0) {
            return 0;
        }
        return 2 * hanoiMoves(disks - 1) + 1; // chuyển tháp nhỏ đi, chuyển cốc đáy, rồi chuyển tháp nhỏ về
    }

    public static void main(String[] args) {
        System.out.println(cups(0) + " " + cups(1) + " " + cups(4) + " " + cups(10));
        System.out.println(sumDigits(9875) + " " + sumDigits(7));
        System.out.println(power(2, 10) + " " + power(5, 0));
        System.out.println(hanoiMoves(3) + " " + hanoiMoves(10) + " " + hanoiMoves(20));
    }
}
