# Bài 02 - Class và Object: trường, phương thức, new, tham chiếu và giá trị mặc định

Mã nguồn đi kèm bài học **Class và Object** (chương 6: Lập trình hướng đối tượng (OOP)) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ tạo đối tượng `Student` và thấy giá trị mặc định của từng kiểu trường, mỗi đối tượng có bản sao trường riêng, hai biến cùng trỏ vào một đối tượng, `==` và `equals` mặc định so sánh địa chỉ, `toString` mặc định in tên lớp và mã băm, lỗi `NullPointerException` khi gọi qua tham chiếu `null`, mảng đối tượng bắt đầu toàn `null`, bộ đếm `static` đếm số đối tượng được tạo, trường đã có giá trị trước dòng đầu của hàm khởi tạo, và năm thông báo lỗi biên dịch về lớp và đối tượng.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
02-class-object/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/oop/classes/
    Student.java                lớp mẫu có trường, phương thức, bộ đếm static
    ClassObjectDemo.java        mọi phép thử và năm lỗi biên dịch
    Javac.java                  biên dịch một chuỗi mã nguồn và trả thông báo lỗi
  src/test/java/dev/canhdominich/core/oop/classes/
    ClassObjectTest.java        chín test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.oop.classes.ClassObjectDemo
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15):

```text
defaults        = name null, score 0, passed false, average 0.0, marks null
independent     = An has 55 (passed) | Binh has 40 (not passed)
two names       = a An has 60 (passed), a == b true, a == c false, a.equals(c) false
array of objects= before [null, null, null], after S3 has 20 (not passed)
before the body = inside the constructor, first line: 0 and 7; afterwards plain 5
```

## Chạy bộ test

```bash
mvn test
```

Kết quả mong đợi: `Tests run: 9, Failures: 0, Errors: 0`.

## Dọn dẹp

```bash
mvn clean
```

## Lỗi thường gặp khi chạy lab

| Triệu chứng | Nguyên nhân | Cách xử lý |
|---|---|---|
| `mvn: command not found` | Maven chưa có trong PATH | Cài Maven 3.9 trở lên và mở lại terminal |

## Thư mục lesson

`lesson/` chứa các chương trình đơn lẻ mà bài học trên nền tảng dùng, mỗi file chạy độc lập bằng `java TênFile.java` (JDK 17 trở lên): `Breakages`, `CardArray`, `CardRefs`, `CardShop`, `Defaults`, `Q3`, `Selfcheck`, `Swap`, `Trace`.
