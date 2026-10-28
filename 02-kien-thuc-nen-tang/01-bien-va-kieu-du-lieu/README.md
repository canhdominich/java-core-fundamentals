# Bài 01 - Biến và kiểu dữ liệu: phạm vi, tràn số, số thực, ký tự, tham chiếu và var

Mã nguồn đi kèm bài học **Biến và kiểu dữ liệu** (chương 2: Kiến thức nền tảng) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ in bảng tám kiểu nguyên thủy với kích thước và khoảng giá trị thật, làm số nguyên tràn và ép kiểu mất dữ liệu, thấy vì sao `0.1 + 0.2` không bằng `0.3` và dùng `BigDecimal` cho tiền, đếm độ dài chuỗi chứa emoji và chữ có dấu, chứng minh hai tham chiếu cùng trỏ một đối tượng và đọc thông báo `NullPointerException`, rồi tạo chín lỗi biên dịch liên quan tới biến chưa gán, `null`, `var` và phạm vi.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
01-bien-va-kieu-du-lieu/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/types/
    Defaults.java               giá trị mặc định của trường theo từng kiểu
    PrimitiveTable.java         in kích thước và khoảng giá trị của tám kiểu nguyên thủy
    Overflow.java               tràn số và ép kiểu mất dữ liệu
    FloatingPoint.java          sai số của số thực nhị phân và BigDecimal
    TextFacts.java              char, UTF-16, ký tự có dấu và chuẩn hóa Unicode
    ReferencesDemo.java         tham chiếu, so sánh bằng == và equals, NullPointerException
    Javac.java                  biên dịch một chuỗi mã nguồn và trả thông báo lỗi
    ScopeAndVar.java            trường static và instance, chín lỗi biên dịch của biến và var
  src/test/java/dev/canhdominich/core/types/
    TypesTest.java              mười test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.types.PrimitiveTable
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.types.Overflow
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.types.FloatingPoint
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.types.TextFacts
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.types.ReferencesDemo
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.types.ScopeAndVar
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15):

```text
Integer.MAX_VALUE + 1        = -2147483648
(byte) 200                   = -56
1_000_000 * 1_000_000        = -727379968
0.1 + 0.2 (double)           = 0.30000000000000004
smiley length()                   = 2, code points = 1
NullPointerException message = Cannot invoke "String.length()" because "name" is null
var name = null;                   -> [line 1: cannot infer type for local variable name]
```

Các chương trình còn lại in đầy đủ các dòng tương tự; thông báo lỗi biên dịch có thể khác ở JDK khác.

## Chạy bộ test

```bash
mvn test
```

Kết quả mong đợi: `Tests run: 10, Failures: 0, Errors: 0`.

## Dọn dẹp

```bash
mvn clean
```

## Lỗi thường gặp khi chạy lab

| Triệu chứng | Nguyên nhân | Cách xử lý |
|---|---|---|
| Thông báo `NullPointerException` hiện `<local1>` thay vì tên biến | Mã biên dịch không có thông tin gỡ lỗi | Biên dịch với `-g` (Maven bật mặc định) |
| `ScopeAndVar` báo `no compiler` | Chạy bằng runtime không có module trình biên dịch | Cài JDK và kiểm tra `javac -version` |

## Thư mục lesson

`lesson/` chứa các chương trình đơn lẻ mà bài học trên nền tảng dùng, mỗi file chạy độc lập bằng `java TênFile.java` (JDK 17 trở lên): `Breakages`, `Casts`, `DailyTotal`, `Factorial`, `Money`, `Overflow`, `Receipt`, `RefDemo`, `Selfcheck`, `TillCheck`, `Trace`.
