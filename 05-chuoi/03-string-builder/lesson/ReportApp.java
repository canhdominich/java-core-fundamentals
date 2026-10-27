public class ReportApp {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("Report").append(" #").append(7);
        System.out.println("step 1: " + sb);

        sb.append(": ");
        for (int i = 1; i <= 3; i++) {
            sb.append("student ").append(i).append(", ");
        }
        System.out.println("step 2: " + sb);

        sb.setLength(sb.length() - 2);
        System.out.println("step 3: " + sb);

        sb.insert(0, "FINAL ");
        System.out.println("step 4: " + sb);

        String result = sb.toString();
        sb.append("!");
        System.out.println("step 5: " + result.endsWith("!") + " " + sb.toString().endsWith("!"));
    }
}
