public class StaticType {
    static String show(Object value) {
        return "Object";
    }

    static String show(String value) {
        return "String";
    }

    public static void main(String[] args) {
        Object runtimeString = "text"; // nhãn là Object nhưng bên trong là chuỗi
        System.out.println(show("text"));
        System.out.println(show(runtimeString)); // trình biên dịch chỉ đọc nhãn Object
        System.out.println(show((String) runtimeString)); // ép kiểu đổi nhãn thành String
    }
}
