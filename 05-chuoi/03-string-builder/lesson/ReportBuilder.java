public class ReportBuilder {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        // append trả về chính sb nên gọi nối tiếp nhau được
        sb.append("Report: ").append(3).append(" students");
        System.out.println(sb + " | length " + sb.length());
        // insert chèn vào vị trí 8, các ký tự phía sau dồn sang phải
        sb.insert(8, "total ");
        System.out.println(sb);
        sb.setCharAt(0, 'r');
        System.out.println(sb);
        // delete(start, end) bỏ ô start đến ô end - 1, ô end không bị bỏ
        sb.delete(8, 14);
        System.out.println(sb);
        // toString chụp lại chữ hiện tại thành một String bất biến
        String result = sb.toString();
        sb.append("!");
        System.out.println(result + " | " + sb);
    }
}
