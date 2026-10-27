public class Increment {
    public static void main(String[] args) {
        int a = 5;
        int b = a++; // lấy giá trị cũ 5 rồi mới tăng a lên 6
        int c = ++a; // tăng a lên 7 rồi mới lấy giá trị 7
        System.out.println("a = " + a + ", b = " + b + ", c = " + c);
        int i = 5;
        i = i++; // phép gán ghi 5 đè lên giá trị 6 vừa tăng
        System.out.println("i = " + i);
    }
}
