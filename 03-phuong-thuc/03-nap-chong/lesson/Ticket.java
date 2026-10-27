public class Ticket {
    static String ticket(int table) {
        return "Table " + table;
    }

    static String ticket(String name) {
        return "For " + name;
    }

    static String ticket(String name, int table) {
        return ticket(name) + " at " + ticket(table).toLowerCase(); // đổi Table thành table
    }

    static String pick(String s) {
        return "String";
    }

    static String pick(StringBuilder b) {
        return "StringBuilder";
    }

    public static void main(String[] args) {
        System.out.println(ticket(5));
        System.out.println(ticket("Linh"));
        System.out.println(ticket("Linh", 5));
        System.out.println(pick((String) null)); // ép kiểu để hết mơ hồ
    }
}
