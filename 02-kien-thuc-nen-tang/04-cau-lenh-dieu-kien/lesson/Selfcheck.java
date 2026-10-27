public class Selfcheck {
    public static void main(String[] args) {
        boolean a = true;
        boolean b = false;
        if (a)
            if (b)
                System.out.println("one");
        else
            System.out.println("two");
        int x = 2;
        switch (x) {
            case 1:
                System.out.println("A");
            case 2:
                System.out.println("B");
            case 3:
                System.out.println("C");
                break;
            case 4:
                System.out.println("D");
        }
    }
}
