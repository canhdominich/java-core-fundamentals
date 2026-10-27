public class SignupForm {
    public static void main(String[] args) {
        String raw = "  An.Nguyen@Example.COM ";
        String email = raw.trim();
        System.out.println("step 1: " + raw.length() + " " + email.length());

        email = email.toLowerCase();
        System.out.println("step 2: " + email);

        int at = email.indexOf('@');
        String domain = email.substring(at + 1);
        System.out.println("step 3: " + at + " " + domain);

        boolean valid = at > 0 && domain.contains(".");
        System.out.println("step 4: " + valid);

        String answer = "YES";
        String empty = null;
        System.out.println("step 5: " + "yes".equalsIgnoreCase(answer) + " " + "yes".equalsIgnoreCase(empty));
    }
}
