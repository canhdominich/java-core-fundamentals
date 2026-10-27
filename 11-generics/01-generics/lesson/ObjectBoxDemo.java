public class ObjectBoxDemo {
    static class ObjectBox {
        private final Object content;

        ObjectBox(Object content) {
            this.content = content;
        }

        Object get() {
            return content;
        }
    }

    public static void main(String[] args) {
        ObjectBox box = new ObjectBox("Latte");
        try {
            // Trình biên dịch cho qua vì get() trả về Object
            Integer cups = (Integer) box.get();
            System.out.println(cups);
        } catch (ClassCastException e) {
            String message = e.getMessage();
            System.out.println("run time: " + message.substring(0, message.indexOf(" (")));
        }
    }
}
