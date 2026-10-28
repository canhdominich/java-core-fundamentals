public class Selfcheck {
    static int f(int n) {
        if (n <= 0) {
            return 0;
        }
        return n + f(n - 2); // mỗi lần đệ quy giảm n đi 2
    }

    public static void main(String[] args) {
        System.out.println(f(5) + " " + f(6));
        Tower a = new Tower(4);
        System.out.println(Tower.built);
        Tower b = new Tower(2);
        System.out.println(Tower.built);
        Tower.reset(); // đặt lại bảng đếm chung về 0
        System.out.println(Tower.built);
        Tower c = new Tower(3);
        System.out.println(Tower.built + " " + a.height + " " + b.height + " " + c.height);
    }
}

class Tower {
    static int built;
    int height;

    Tower(int height) {
        this.height = height;
        built++;
    }

    static void reset() {
        built = 0;
    }
}
