public class Forms {
    public static void main(String[] args) {
        int stock = 3;
        int whilePacks = 0;
        while (stock >= 4) { // kiểm tra trước nên sai ngay từ đầu, thân chạy 0 lần
            stock -= 4;
            whilePacks++;
        }
        System.out.println("while: " + whilePacks + " packs, stock " + stock);
        int doPacks = 0;
        do {
            stock -= 4; // thân chạy trước rồi mới kiểm tra, nên luôn chạy ít nhất một lần
            doPacks++;
        } while (stock >= 4);
        System.out.println("do-while: " + doPacks + " pack, stock " + stock);
    }
}
