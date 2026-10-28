# Bài 03 - Nạp chồng phương thức: chọn phương thức lúc biên dịch

Mã nguồn đi kèm bài học **Nạp chồng phương thức (Overloading)** (chương 3: Phương thức) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ chạy các phương thức cùng tên khác tham số và thấy số lượng, kiểu và thứ tự kiểu quyết định phương thức được gọi, đo ba giai đoạn chọn của trình biên dịch (mở rộng kiểu, đóng gói, `varargs`), chứng minh rằng nạp chồng được chọn theo kiểu khai báo chứ không theo kiểu thật của đối tượng, xem bẫy `List.remove` giữa chỉ số và giá trị, và đọc bốn thông báo lỗi biên dịch của nạp chồng.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
03-nap-chong/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/overloading/
    Calculator.java            add với số lượng, kiểu và thứ tự kiểu khác nhau
    Resolution.java            mở rộng kiểu, đóng gói và varargs: giai đoạn nào thắng
    StaticDispatch.java        chọn theo kiểu khai báo, List.remove, bốn lỗi biên dịch
    Javac.java                 biên dịch một chuỗi mã nguồn và trả thông báo lỗi
  src/test/java/dev/canhdominich/core/overloading/
    OverloadingTest.java       chín test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.overloading.Calculator
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.overloading.Resolution
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.overloading.StaticDispatch
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15):

```text
add('a', 'b')       = 195
widening(5)          = long
boxing(5)            = Integer
show(Object holding "text")  = Object
remove(1) as an index        = [10, 30, 1]
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

`lesson/` chứa các chương trình đơn lẻ mà bài học trên nền tảng dùng, mỗi file chạy độc lập bằng `java TênFile.java` (JDK 17 trở lên): `Breakages`, `Deep`, `Phases`, `Pricing`, `Printer`, `Selfcheck`, `StaticType`, `Ticket`.
