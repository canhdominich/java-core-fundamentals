import javax.lang.model.SourceVersion;

public class NameCheck {
    // Tên hợp lệ khi đúng ký tự cho phép và không trùng từ khóa
    static void show(String name) {
        boolean valid = SourceVersion.isIdentifier(name) && !SourceVersion.isKeyword(name);
        System.out.println(name + " valid=" + valid);
    }

    public static void main(String[] args) {
        show("ticketCount");
        show("TicketCount");
        show("ticket_count");
        show("$fee");
        show("2tickets");
        show("ticket-count");
        show("class");
    }
}
