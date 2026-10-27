import java.util.ArrayList;
import java.util.List;

public class Skip {
    public static void main(String[] args) {
        // List.of chỉ đọc, nên phải bọc vào ArrayList mới xoá được
        List<String> orders = new ArrayList<>(
                List.of("Latte", "CANCELLED", "CANCELLED", "Mocha", "Tea", "Latte"));
        // size() được tính lại ở mỗi vòng, i luôn tăng dù có xoá hay không
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).equals("CANCELLED")) {
                orders.remove(i);  // các ghế sau dồn trái, phần tử kế tiếp lọt vào ghế i
            }
        }
        System.out.println(orders + " size " + orders.size());
    }
}
