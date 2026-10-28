public class TowerPlus {
    static int cups(int floors) {
        if (floors <= 1) {
            return 1;
        }
        return floors + cups(floors - 1);
    }

    public static void main(String[] args) {
        System.out.println("step 1: " + cups(4));
        Tower a = new Tower(4);
        Tower b = new Tower(2);
        System.out.println("step 2: " + a.total() + " " + b.total());
        System.out.println("step 3: " + Tower.built);
        Tower.reset();
        System.out.println("step 4: " + Tower.built);
    }
}

class Tower {
    static int built; // một bản cho cả class
    int height; // mỗi tháp một bản

    Tower(int height) {
        this.height = height;
        built++;
    }

    int total() {
        return TowerPlus.cups(height);
    }

    static void reset() {
        built = 0; // static chỉ chạm được thành viên static
    }
}
