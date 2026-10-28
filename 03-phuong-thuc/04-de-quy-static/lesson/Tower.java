public class Tower {
    static int built; // một bản cho cả class: số tháp đã dựng
    int height; // mỗi tháp một bản: số tầng của tháp đó

    Tower(int height) {
        this.height = height;
        built++;
    }

    static int cups(int floors) { // floors: mỗi lời gọi một bản
        if (floors <= 1) { // trường hợp cơ sở: tầng trên cùng chỉ có 1 cốc
            return 1;
        }
        return floors + cups(floors - 1); // bước thu nhỏ: tầng này cộng phần tháp phía trên
    }

    int total() {
        return cups(height); // đọc height của chính tháp này
    }

    public static void main(String[] args) {
        Tower a = new Tower(4);
        Tower b = new Tower(2);
        System.out.println("a " + a.total());
        System.out.println("b " + b.total());
        System.out.println("built " + Tower.built);
    }
}
