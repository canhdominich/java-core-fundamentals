public class Hello {
    // Ghép lời chào từ một cái tên
    static String greeting(String name) {
        return "Hello, " + name + "!";
    }

    // Lệnh java tìm đúng chữ ký này để bắt đầu chương trình
    public static void main(String[] args) {
        String name = "Java";
        // args là các từ gõ sau tên lớp, ví dụ: java Hello Lan
        if (args.length > 0) {
            name = args[0];
        }
        System.out.println(greeting(name));
    }
}
