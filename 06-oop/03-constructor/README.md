# Bài 03 - Constructor: nạp chồng, this(...), kiểm tra dữ liệu, thứ tự khởi tạo và bẫy khi gọi phương thức ghi đè

Mã nguồn đi kèm bài học **Constructor** (chương 6: Lập trình hướng đối tượng (OOP)) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ chạy các hàm khởi tạo: lỗi `name = name` làm trường vẫn `null`, hàm khởi tạo từ chối đối tượng không hợp lệ, nạp chồng, hàm khởi tạo sao chép, chuỗi `this(...)` và thứ tự thân hàm chạy từ trong ra ngoài, thứ tự khởi tạo giữa lớp cha và lớp con, bẫy khi hàm khởi tạo của lớp cha gọi phương thức bị lớp con ghi đè và thấy trường mặc định, hàm khởi tạo `private` với phương thức tạo (factory), sao chép phòng vệ một mảng nhận vào, và tám thông báo lỗi biên dịch về hàm khởi tạo.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
03-constructor/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/oop/constructors/
    BuggyPerson.java            lỗi name = name khiến trường vẫn null
    Person.java                 hàm khởi tạo kiểm tra dữ liệu, nạp chồng, this(...), sao chép
    Order.java                  ba hàm khởi tạo nối nhau bằng this(...)
    Parent.java                 lớp cha có trường, khối khởi tạo và gọi describe()
    Child.java                  lớp con ghi đè describe() và có trường riêng
    Money.java                  hàm khởi tạo private và phương thức tạo of(...)
    Polygon.java                lưu mảng nhận vào có hoặc không sao chép
    Trace.java                  ghi lại thứ tự các bước để in ra
    ConstructorDemo.java        chạy mọi ví dụ
    ConstructorErrors.java      tám lỗi biên dịch về hàm khởi tạo
    Javac.java                  biên dịch một chuỗi mã nguồn và trả thông báo lỗi
  src/test/java/dev/canhdominich/core/oop/constructors/
    ConstructorTest.java        chín test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.oop.constructors.ConstructorDemo
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.oop.constructors.ConstructorErrors
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15):

```text
shadowing        = buggy: name null, age 0 | fixed: name An, age 30
chaining         = [Order(id, quantity, price) body, Order(id, quantity) body, Order(id) body, A1 x1 at 9.99]
parent trap      = seen by the parent constructor: label=null, label afterwards: ready
defensive copy   = after changing the caller array: sharing 99, copying 1
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

`lesson/` chứa các chương trình đơn lẻ mà bài học trên nền tảng dùng, mỗi file chạy độc lập bằng `java TênFile.java` (JDK 17 trở lên): `CardBirth`, `CardCopy`, `CardShop`, `CardTier`, `Chain`, `InitOrder`, `Q2`, `Shadow`.
