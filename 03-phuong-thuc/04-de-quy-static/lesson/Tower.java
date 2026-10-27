public class Tower {
    static int cups(int floors) {
        if (floors == 1) { // trường hợp cơ sở: tầng trên cùng chỉ có 1 cốc
            return 1;
        }
        return floors + cups(floors - 1); // bước thu nhỏ: tầng này cộng phần tháp phía trên
    }

    public static void main(String[] args) {
        System.out.println("cups(1) = " + cups(1));
        System.out.println("cups(4) = " + cups(4));
    }
}
