public class Braces {
    public static void main(String[] args) {
        boolean member = true;
        boolean bigOrder = false;
        if (member) {
            if (bigOrder) {
                System.out.println("big member order");
            }
        } else { // ngoặc nhọn buộc else thuộc if (member)
            System.out.println("not a member");
        }
        member = false;
        if (member) {
            if (bigOrder) {
                System.out.println("big member order");
            }
        } else {
            System.out.println("not a member");
        }
    }
}
