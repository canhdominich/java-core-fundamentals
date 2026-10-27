public class Footer {
    public static void main(String[] args) {
        System.out.println(new Plain().render());
        System.out.println(new Thanks().render());
    }
}

abstract class Slip {
    abstract String body();

    // Móc nối mặc định rỗng: con có thể ghi đè hoặc bỏ qua
    String footer() {
        return "";
    }

    final String render() {
        return body() + footer();
    }
}

class Plain extends Slip {
    @Override
    String body() {
        return "total 90000";
    }
}

class Thanks extends Slip {
    @Override
    String body() {
        return "total 90000";
    }

    @Override
    String footer() {
        return " - thank you";
    }
}
