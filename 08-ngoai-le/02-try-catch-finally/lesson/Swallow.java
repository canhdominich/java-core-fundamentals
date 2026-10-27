public class Swallow {
    public static void main(String[] args) {
        System.out.println("swallow abc " + swallow("abc"));
        System.out.println("swallow 0 " + swallow("0"));
        System.out.println("report abc " + report("abc"));
        System.out.println("report 0 " + report("0"));
    }

    static int swallow(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            // Catch rỗng: lỗi biến mất, không ai biết đã có chuyện
        }
        return 0;
    }

    static int report(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            System.out.println("bad number: " + text);
            return -1;
        }
    }
}
