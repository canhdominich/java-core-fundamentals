public class Deeper {
    public static void main(String[] args) {
        int[][] grid = {{1, 2, 3}, {4, 5, 6}, {7, 4, 9}};
        int row = -1;
        int col = -1;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (grid[r][c] == 4) {
                    row = r;
                    col = c;
                    break; // chỉ thoát vòng trong, vòng ngoài vẫn chạy tiếp
                }
            }
        }
        System.out.println("plain break: (" + row + "," + col + ")");
        search:
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (grid[r][c] == 4) {
                    row = r;
                    col = c;
                    break search; // thoát cả hai vòng
                }
            }
        }
        System.out.println("labeled break: (" + row + "," + col + ")");
        int runs = 0;
        double d = 0;
        while (d != 1.0 && runs < 14) { // giới hạn 14 vòng để chương trình dừng
            d += 0.1;
            runs++;
        }
        System.out.println("float counter: " + runs + " runs, d = " + d);
    }
}
