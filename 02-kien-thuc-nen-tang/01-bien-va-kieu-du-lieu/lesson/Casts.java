public class Casts {
    public static void main(String[] args) {
        System.out.println("(byte) 200 = " + (byte) 200); // 200 không vừa 8 bit nên quay vòng
        System.out.println("(short) 40000 = " + (short) 40000);
        System.out.println("(int) 3_000_000_000L = " + (int) 3_000_000_000L);
        System.out.println("(int) 3.99 = " + (int) 3.99); // cắt phần lẻ, không làm tròn
        System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE);
        System.out.println("Long.MAX_VALUE = " + Long.MAX_VALUE);
    }
}
