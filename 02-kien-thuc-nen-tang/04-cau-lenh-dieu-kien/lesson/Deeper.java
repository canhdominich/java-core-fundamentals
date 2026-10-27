public class Deeper {
    enum Day { MON, TUE, SAT }

    public static void main(String[] args) {
        Day day = Day.SAT;
        String kind = switch (day) { // enum liệt kê đủ hằng nên không cần default
            case MON, TUE -> "weekday";
            case SAT -> "weekend";
        };
        System.out.println(day + " " + kind);
        String text = null;
        try {
            switch (text) {
                case "a" -> System.out.println("a");
                default -> System.out.println("other");
            }
        } catch (NullPointerException e) {
            System.out.println("NullPointerException: " + e.getMessage());
        }
    }
}
