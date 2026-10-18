# Bài 07 - Trừu tượng: lớp abstract, phương thức abstract và mẫu Template Method

Mã nguồn đi kèm bài học **Trừu tượng (Abstraction)** (chương 6: Lập trình hướng đối tượng (OOP)) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ chạy lớp `abstract` `Shape` với `area()` và `perimeter()` chưa có thân, dùng nó qua kiểu cha trong khi `Circle` và `Rectangle` cung cấp phần tính toán, quan sát hàm khởi tạo của lớp trừu tượng chạy trước thân hàm khởi tạo của lớp con, dùng lớp `Exporter` với phương thức `final` cố định thứ tự các bước (mẫu Template Method) và hai lớp con `CsvExporter`, `HtmlExporter` điền các bước còn thiếu, rồi đọc tám thông báo lỗi biên dịch bảo vệ hợp đồng trừu tượng.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
07-truu-tuong/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/oop/abstraction/
    Shape.java                  lớp abstract có hàm khởi tạo, phương thức abstract và phương thức cụ thể
    Circle.java                 hình tròn cài đặt area và perimeter
    Rectangle.java              hình chữ nhật cài đặt area và perimeter
    Exporter.java               lớp abstract với phương thức export final làm khuôn
    CsvExporter.java            xuất theo dạng CSV
    HtmlExporter.java           xuất theo dạng HTML có footer
    AbstractionDemo.java        chạy mọi ví dụ và tám lỗi biên dịch
    Javac.java                  biên dịch một chuỗi mã nguồn và trả thông báo lỗi
  src/test/java/dev/canhdominich/core/oop/abstraction/
    AbstractionTest.java        sáu test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.oop.abstraction.AbstractionDemo
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15):

```text
shapes             = [circle: area 3.14, perimeter 6.28, rectangle: area 12.00, perimeter 14.00]
construction order = [Shape(rectangle), Rectangle(2.0, 5.0)]
new on an abstract class    = [line 1: A is abstract; cannot be instantiated]
abstract and static         = [line 1: illegal combination of modifiers: abstract and static]
```

## Chạy bộ test

```bash
mvn test
```

Kết quả mong đợi: `Tests run: 6, Failures: 0, Errors: 0`.

## Dọn dẹp

```bash
mvn clean
```

## Lỗi thường gặp khi chạy lab

| Triệu chứng | Nguyên nhân | Cách xử lý |
|---|---|---|
| `mvn: command not found` | Maven chưa có trong PATH | Cài Maven 3.9 trở lên và mở lại terminal |
