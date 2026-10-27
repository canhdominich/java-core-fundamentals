public class Dangling {
    public static void main(String[] args) {
        boolean member = true;
        boolean bigOrder = false;
        if (member)
            if (bigOrder)
                System.out.println("big member order");
        else // cách thụt dòng gợi ý thuộc if (member), nhưng thật ra thuộc if (bigOrder)
            System.out.println("who gets this else?");
    }
}
