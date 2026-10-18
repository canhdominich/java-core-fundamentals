# Bài 01 - Ngoại lệ và phân cấp: Throwable, checked và unchecked, thông báo thường gặp

Mã nguồn đi kèm bài học **Ngoại lệ và phân cấp** (chương 8: Xử lý ngoại lệ) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ in chuỗi kế thừa của bốn loại ngoại lệ để thấy mọi thứ đều xuất phát từ `Throwable`, phân loại checked và unchecked, gây ra mười ba ngoại lệ phổ biến và đọc thông báo của từng cái (chia cho 0, `NullPointerException` kèm tên biến, chỉ số mảng, ép kiểu, `NumberFormatException`, danh sách bất biến, tệp không có), chạy một chương trình con để xem ngoại lệ không bị bắt in ra gì và chương trình thoát với mã nào, và đọc các thông báo lỗi biên dịch của ngoại lệ kiểm tra.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
01-phan-cap/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/exceptions/hierarchy/
    Hierarchy.java              in chuỗi kế thừa và phân loại checked hoặc unchecked
    Action.java                 interface hàm có thể ném Exception
    CommonExceptions.java       gây ra mười ba ngoại lệ phổ biến và đọc thông báo
    Boom.java                   chương trình ném ngoại lệ không bị bắt qua ba tầng gọi
    HierarchyDemo.java          chạy mọi ví dụ và các lỗi biên dịch
    Javac.java                  biên dịch một chuỗi mã nguồn và trả thông báo lỗi
  src/test/java/dev/canhdominich/core/exceptions/hierarchy/
    HierarchyTest.java          sáu test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.exceptions.hierarchy.HierarchyDemo
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15):

```text
chain: FileNotFoundException extends IOException extends Exception extends Throwable extends Object
divide by zero           : ArithmeticException: / by zero
uncaught exception in a new process: stdout [before], exit code 1, first stderr line [Exception in thread "main" java.lang.IllegalStateException: boom], ...
throw a checked exception  = [line 1: unreported exception java.lang.Exception; must be caught or declared to be thrown]
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
