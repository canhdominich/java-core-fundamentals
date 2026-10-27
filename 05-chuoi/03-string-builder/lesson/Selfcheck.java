public class Selfcheck {
    static String join(String[] parts, String sep) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            if (i > 0) {
                sb.append(sep);
            }
            sb.append(parts[i]);
        }
        return sb.toString();
    }

    static String report(String[] names, int[] scores) {
        StringBuilder sb = new StringBuilder();
        int total = 0;
        for (int i = 0; i < names.length; i++) {
            sb.append(names[i]).append(": ").append(scores[i]).append('\n');
            total += scores[i];
        }
        sb.append("average ").append((double) total / names.length);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("b2 " + join(new String[]{"An", "Binh", "Chi"}, ", ") + " | [" + join(new String[]{}, ", ") + "]");
        StringBuilder x = new StringBuilder("hi");
        StringBuilder y = new StringBuilder("hi");
        System.out.println("b3 " + x.equals(y) + " " + x.toString().equals(y.toString()) + " " + x.compareTo(y) + " " + x.toString().contentEquals(y));
        System.out.println("b4\n" + report(new String[]{"An", "Binh", "Chi"}, new int[]{8, 9, 7}));
        System.out.println("pal " + new StringBuilder("level").reverse().toString().equals("level") + " " + new StringBuilder("Java").reverse().toString().equals("Java"));
    }
}
