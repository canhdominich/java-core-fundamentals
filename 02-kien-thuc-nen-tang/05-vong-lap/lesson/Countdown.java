public class Countdown {
    public static void main(String[] args) {
        for (int i = 10; i >= 1; i -= 3) { // bước cập nhật có thể là bất kỳ câu lệnh nào
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
