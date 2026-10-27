public class HashDemo {
    public static void main(String[] args) {
        String a = "hello";
        String b = new String("hello");
        // hashCode tính từ nội dung chữ, nên hai chuỗi bằng nhau luôn cùng mã
        System.out.println("hash " + a.hashCode() + " " + b.hashCode());
        System.out.println("equal strings, same hash " + (a.hashCode() == b.hashCode()));
        // Ngược lại thì không đúng: hai chuỗi khác nhau vẫn có thể trùng mã
        System.out.println("Aa hash " + "Aa".hashCode() + ", BB hash " + "BB".hashCode());
        System.out.println("Aa equals BB " + "Aa".equals("BB"));
        System.out.println("empty hash " + "".hashCode());
    }
}
