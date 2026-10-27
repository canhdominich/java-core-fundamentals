public class PoolDemo {
    public static void main(String[] args) {
        // Hai literal giống nhau dùng chung một đối tượng trong pool
        String a = "java";
        String b = "java";
        // new luôn tạo đối tượng mới, nằm ngoài pool
        String c = new String("java");
        // intern trả về đối tượng của pool có cùng chữ
        String d = c.intern();
        // Nối lúc chạy cũng tạo đối tượng mới ngoài pool
        String e = "ja" + new String("va");
        System.out.println("a == b " + (a == b));
        System.out.println("a == c " + (a == c));
        System.out.println("a == d " + (a == d));
        System.out.println("a == e " + (a == e));
        System.out.println("a == e.intern() " + (a == e.intern()));
        System.out.println("a.equals(c) " + a.equals(c));
        String original = "cat";
        String alias = original;
        // Gán lại cho original, chuỗi "cat" cũ không bị sửa
        original = original + "s";
        System.out.println(original + " " + alias + " " + (original == alias));
    }
}
