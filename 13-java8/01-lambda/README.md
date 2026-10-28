# Bài 01 - Lambda và interface hàm: cú pháp, tham chiếu phương thức, ghép hàm, ngữ cảnh biến và this

Mã nguồn đi kèm bài học **Lambda và Functional Interface** (chương 13: Java 8 trở lên) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ viết các dạng lambda (`Function`, `BiFunction`, `Supplier`, `Consumer`, `UnaryOperator`, `Predicate`, khối nhiều dòng), bốn loại tham chiếu phương thức (tĩnh, đối tượng cụ thể, đối tượng bất kỳ của một kiểu, hàm khởi tạo), ghép hàm bằng `andThen`, `compose`, `and`, `negate`, thấy lambda bắt giá trị chứ không bắt biến và `Supplier` trì hoãn việc tính toán, thấy `this` trong lambda là đối tượng bao ngoài còn trong lớp ẩn danh là chính nó, và đọc sáu thông báo lỗi biên dịch của lambda.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
01-lambda/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/java8/lambda/
    Syntax.java                 các dạng lambda, tham chiếu phương thức, ghép hàm, bắt biến, trì hoãn
    ThisMeaning.java            this trong lambda so với trong lớp ẩn danh
    LambdaDemo.java             chạy mọi ví dụ và sáu lỗi biên dịch
    Javac.java                  biên dịch một chuỗi mã nguồn và trả thông báo lỗi
  src/test/java/dev/canhdominich/core/java8/lambda/
    LambdaTest.java             tám test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.java8.lambda.LambdaDemo
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15):

```text
composition        = andThen(plusOne, timesTwo) of 5 is 12, compose is 11, positive and even for 4 true, positive and even for -4 false, negate of even for 3 true
this in a lambda   = this is the outer object
this in an anonymous class = this is the anonymous object
modify a captured local      = [line 1: local variables referenced from a lambda expression must be final or effectively final]
```

## Chạy bộ test

```bash
mvn test
```

Kết quả mong đợi: `Tests run: 8, Failures: 0, Errors: 0`.

## Dọn dẹp

```bash
mvn clean
```

## Lỗi thường gặp khi chạy lab

| Triệu chứng | Nguyên nhân | Cách xử lý |
|---|---|---|
| `mvn: command not found` | Maven chưa có trong PATH | Cài Maven 3.9 trở lên và mở lại terminal |

## Thư mục lesson

`lesson/` chứa các chương trình đơn lẻ mà bài học trên nền tảng dùng, mỗi file chạy độc lập bằng `java TênFile.java` (JDK 17 trở lên): `BoardLambda`, `Capture`, `Check44`, `OrderBoard`, `Screen`.
