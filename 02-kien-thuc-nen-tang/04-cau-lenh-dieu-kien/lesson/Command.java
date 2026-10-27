public class Command {
    public static void main(String[] args) {
        String command = null;
        String reply;
        if (command == null) { // switch trên chuỗi null sẽ ném NullPointerException nên chặn trước
            reply = "no command";
        } else {
            reply = switch (command) {
                case "start" -> "starting";
                case "stop" -> "stopping";
                default -> "unknown command " + command;
            };
        }
        System.out.println(reply);
    }
}
