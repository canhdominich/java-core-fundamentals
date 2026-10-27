import java.math.BigInteger;

public class Factorial {
    public static void main(String[] args) {
        long f20 = 1L * 2 * 3 * 4 * 5 * 6 * 7 * 8 * 9 * 10 * 11 * 12 * 13 * 14 * 15 * 16 * 17 * 18 * 19 * 20;
        long f21 = f20 * 21; // vượt Long.MAX_VALUE nên quay vòng ra số âm
        BigInteger exact = BigInteger.valueOf(f20).multiply(BigInteger.valueOf(21)); // BigInteger không có giới hạn
        System.out.println("20! = " + f20);
        System.out.println("21! (long) = " + f21);
        System.out.println("21! (BigInteger) = " + exact);
    }
}
