public class NoBase {
    static int broken(int floors) {
        return floors + broken(floors - 1); // không có trường hợp cơ sở nên không bao giờ dừng
    }

    public static void main(String[] args) {
        try {
            broken(4);
        } catch (StackOverflowError e) { // ngăn xếp đầy thì máy ảo ném lỗi này
            System.out.println(e.getClass().getSimpleName() + ", message " + e.getMessage());
        }
    }
}
