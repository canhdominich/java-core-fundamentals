public class OsFacts {
    public static void main(String[] args) {
        // Các giá trị này do hệ điều hành quyết định, máy khác có thể in khác
        System.out.println("os.name        = " + System.getProperty("os.name"));
        System.out.println("path separator = " + System.getProperty("path.separator"));
        System.out.println("file separator = " + System.getProperty("file.separator"));
        System.out.println("line separator = " + System.lineSeparator().length() + " char(s)");
    }
}
