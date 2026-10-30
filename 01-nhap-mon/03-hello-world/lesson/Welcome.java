public class Welcome {
    static String welcome(String name) {
        return "Welcome to Cafe Chu Tu, " + name + "!";
    }

    public static void main(String[] args) {
        // Chưa có tên khách thì in cách dùng rồi thoát với mã 2
        if (args.length == 0) {
            System.out.println("usage: java Welcome.java <name>");
            System.exit(2);
        }
        System.out.println(welcome(args[0]));
    }
}
