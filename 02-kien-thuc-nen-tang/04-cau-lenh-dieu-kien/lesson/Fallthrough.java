public class Fallthrough {
    public static void main(String[] args) {
        int zone = 1;
        switch (zone) {
            case 1:
                System.out.println("fee: 20000 (Pho Rom Ra)"); // không có break nên chạy tiếp xuống nhãn dưới
            case 2:
                System.out.println("fee: 30000 (Xom Vui)");
            default:
                System.out.println("fee: 35000 (other)");
        }
    }
}
