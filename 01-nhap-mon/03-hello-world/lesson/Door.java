public class Door {
    public static void main(String[] args) {
        // Cần đủ hai từ: đồ uống và số bàn
        if (args.length < 2) {
            System.out.println("usage: java Door.java <drink> <table>");
            System.exit(2);
        }
        System.out.println("Table " + args[1] + ": " + args[0]);
    }
}
