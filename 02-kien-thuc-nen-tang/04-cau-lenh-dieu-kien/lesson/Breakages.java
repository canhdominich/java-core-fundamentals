public class Breakages {
    public static void main(String[] args) {
        int points = 79;
        String wrong = "NONE";
        if (points >= 50) { // thứ tự sai, chặn các nhánh sau
            wrong = "BRONZE";
        } else if (points >= 80) {
            wrong = "GOLD";
        }
        System.out.println("1: " + wrong);
        String typed = new String("yes");
        System.out.println("2: == " + (typed == "yes") + ", equals " + typed.equals("yes")); // == so tờ phiếu, không so nội dung
        int zone = 1;
        StringBuilder out = new StringBuilder();
        switch (zone) {
            case 1:
                out.append("A "); // thiếu break nên chạy xuyên
            case 2:
                out.append("B ");
        }
        System.out.println("3: " + out.toString().trim());
    }
}
