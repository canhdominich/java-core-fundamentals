public class Deeper {
    public static void main(String[] args) {
        byte b = 10;
        b += 5; // phép gán gộp tự ép kết quả về kiểu byte
        System.out.println("b += 5 = " + b);
        b += 300; // quay vòng im lặng về 59
        System.out.println("then b += 300 = " + b);
        int y = 10;
        y *= 2.5; // kết quả thực 25.0 bị cắt về int
        System.out.println("y *= 2.5 = " + y);
        System.out.println("Math.floorMod(-7, 3) = " + Math.floorMod(-7, 3)); // phần dư không âm
        System.out.println("5.0 / 0 = " + (5.0 / 0)); // chia số thực cho 0 không ném lỗi
        System.out.println("-8 >> 1 = " + (-8 >> 1) + ", -8 >>> 1 = " + (-8 >>> 1));
        Integer small1 = 127, small2 = 127, big1 = 128, big2 = 128;
        System.out.println("127 == 127: " + (small1 == small2) + ", 128 == 128: " + (big1 == big2)); // Integer nhỏ được dùng chung
    }
}
