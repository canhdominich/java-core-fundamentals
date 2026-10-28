# Bài 08 - static và final: hằng số, khởi tạo lớp, đánh số bằng bộ đếm chung và quy tắc gán một lần

Mã nguồn đi kèm bài học **Từ khóa static và final** (chương 6: Lập trình hướng đối tượng (OOP)) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ chạy ví dụ để thấy khi nào lớp được khởi tạo (hằng số biên dịch không kích hoạt, gọi phương thức hoặc đọc trường tính lúc chạy thì có), vì sao `static final` với danh sách hoặc mảng vẫn bị sửa nội dung được, cách trường `static` đánh số hoá đơn và trường `final` giữ giá trị gán một lần, `final` cho biến cục bộ và tham chiếu, và bảy thông báo lỗi biên dịch của `final`.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
08-static-final/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/oop/staticfinal/
    Lazy.java                   hằng số biên dịch, trường tính lúc chạy, khối static, hàm khởi tạo
    LazyProbe.java              chương trình con đo thứ tự khởi tạo lớp trong một tiến trình mới
    Config.java                 hằng số static final, danh sách và mảng vẫn sửa được nội dung
    Invoice.java                bộ đếm static đánh số và các trường final gán một lần
    Trace.java                  ghi lại thứ tự các bước để in ra
    StaticFinalDemo.java        chạy mọi ví dụ và bảy lỗi biên dịch
    Javac.java                  biên dịch một chuỗi mã nguồn và trả thông báo lỗi
  src/test/java/dev/canhdominich/core/oop/staticfinal/
    StaticFinalTest.java        sáu test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.oop.staticfinal.StaticFinalDemo
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15):

```text
first use is a method   = [read the compile-time constant 42, field initializer runs, static block runs, helper runs, second use, constructor runs]
final fields          = numbers 1000, 1001, next customer Binh, amount 700
assign a final local      = [line 1: cannot assign a value to final variable x]
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

## Thư mục lesson

`lesson/` chứa các chương trình đơn lẻ mà bài học trên nền tảng dùng, mỗi file chạy độc lập bằng `java TênFile.java` (JDK 17 trở lên): `Limit`, `Probe`, `Selfcheck`, `Shop`, `ShopPlus`. Thư mục con: `breakages/`, `inlined/`.
