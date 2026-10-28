# Bài 01 - String cơ bản: bất biến, cắt, tìm, tách, so sánh, nối và định dạng

Mã nguồn đi kèm bài học **String cơ bản** (chương 5: Chuỗi) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ chạy các phương thức thường dùng của `String` và thấy vì sao chúng luôn trả chuỗi mới, các lỗi chỉ số khi cắt chuỗi, khác biệt giữa `replace` và `replaceAll`, giữa `trim` và `strip`, những bất ngờ của `split` (phần tử rỗng ở cuối, tách theo dấu chấm), so sánh bằng `==`, `equals`, `compareTo`, cách `+` đánh giá từ trái sang phải với số, ký tự và `null`, cách dùng `format`, và bảy thông báo lỗi biên dịch hay gặp với chuỗi.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
01-string-co-ban/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/strings/
    StringBasics.java           bất biến, cắt, tìm, thay thế, khoảng trắng, tách, dựng chuỗi
    StringCompare.java          ==, equals, null, hoa thường, compareTo, hằng biên dịch
    Concatenation.java          nối bằng + với số, ký tự, null, số thực, format
    StringErrors.java           bảy lỗi biên dịch với chuỗi
    Javac.java                  biên dịch một chuỗi mã nguồn và trả thông báo lỗi
  src/test/java/dev/canhdominich/core/strings/
    StringsTest.java            mười lăm test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.strings.StringBasics
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.strings.StringCompare
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.strings.Concatenation
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.strings.StringErrors
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15):

```text
immutability = name java, toUpperCase JAVA, replace jovo, name again java
replacing    = replace dot a-b-c, replaceAll dot -----, replaceAll escaped a-b-c, replaceFirst a-b.c
equality         = literal == literal true, literal == new String false, equals true, interned == literal true
numbers   = 1 + 2 + "3" = 33, "1" + 2 + 3 = 123, "1" + (2 + 3) = 15
```

Thông báo của `charAt` ngoài khoảng (`String index out of range: 5`) là của JDK 17; các bản JDK mới hơn dùng câu chữ khác.

## Chạy bộ test

```bash
mvn test
```

Kết quả mong đợi: `Tests run: 15, Failures: 0, Errors: 0`.

## Dọn dẹp

```bash
mvn clean
```

## Lỗi thường gặp khi chạy lab

| Triệu chứng | Nguyên nhân | Cách xử lý |
|---|---|---|
| `mvn: command not found` | Maven chưa có trong PATH | Cài Maven 3.9 trở lên và mở lại terminal |

## Thư mục lesson

`lesson/` chứa các chương trình đơn lẻ mà bài học trên nền tảng dùng, mỗi file chạy độc lập bằng `java TênFile.java` (JDK 17 trở lên): `Breakages`, `Compare`, `Extras`, `NameFixer`, `Selfcheck`, `SignupForm`.
