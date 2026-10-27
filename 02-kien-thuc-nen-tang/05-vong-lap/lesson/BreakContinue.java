public class BreakContinue {
    public static void main(String[] args) {
        int[] shelf = {5, 8, 0, 3, 0};
        int firstEmpty = -1;
        for (int i = 0; i < shelf.length; i++) {
            if (shelf[i] == 0) {
                firstEmpty = i;
                break; // thoát hẳn vòng lặp khi đã tìm thấy
            }
        }
        int filled = 0;
        for (int units : shelf) {
            if (units == 0) {
                continue; // bỏ qua phần còn lại của vòng này, sang vòng sau
            }
            filled++;
        }
        System.out.println("first empty at " + firstEmpty + ", filled slots " + filled);
    }
}
