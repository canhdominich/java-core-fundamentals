# Bài 06 - Đa hình: ghi đè, @Override, equals, phương thức static và private, kiểu trả về hiệp biến

Mã nguồn đi kèm bài học **Đa hình (Polymorphism)** (chương 6: Lập trình hướng đối tượng (OOP)) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ chạy một giỏ hàng dùng ba kiểu giảm giá qua cùng một lời gọi, rồi tái hiện bẫy `equals(Point)` nạp chồng thay vì ghi đè (làm `contains` và `HashSet` không tìm thấy phần tử), so với `equals(Object)` đúng; thấy phương thức `static` bị che chứ không bị ghi đè, phương thức `private` không bị thay thế, kiểu trả về hiệp biến, và năm thông báo lỗi biên dịch về ghi đè.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
06-da-hinh/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/oop/polymorphism/
    Discount.java               lớp cha của các kiểu giảm giá
    PercentDiscount.java        giảm theo phần trăm
    FixedDiscount.java          giảm một số tiền cố định
    Checkout.java               áp dụng danh sách giảm giá qua cùng một lời gọi
    BrokenPoint.java            equals nhận BrokenPoint: nạp chồng chứ không ghi đè
    GoodPoint.java              equals nhận Object kèm hashCode và toString
    Base.java                   lớp cha có phương thức static, private và trả về kiểu Base
    Derived.java                lớp con che phương thức static và trả về kiểu Derived
    PolymorphismDemo.java       chạy mọi ví dụ và năm lỗi biên dịch
    Javac.java                  biên dịch một chuỗi mã nguồn và trả thông báo lỗi
  src/test/java/dev/canhdominich/core/oop/polymorphism/
    PolymorphismTest.java       bảy test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.oop.polymorphism.PolymorphismDemo
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15):

```text
strategies       = price 100000 -> 85000 after 10 percent, 5000 off, none; price 3000 -> 0
equals(Point)    = a.equals(b) true, a.equals((Object) b) false, list.contains(b) false, set.contains(b) false
equals(Object)   = a.equals(b) true, a.equals((Object) b) true, list.contains(b) true, set.contains(b) true, toString (1, 2)
@Override with a typo      = [line 1: method does not override or implement a method from a supertype]
```

## Chạy bộ test

```bash
mvn test
```

Kết quả mong đợi: `Tests run: 7, Failures: 0, Errors: 0`.

## Dọn dẹp

```bash
mvn clean
```

## Lỗi thường gặp khi chạy lab

| Triệu chứng | Nguyên nhân | Cách xử lý |
|---|---|---|
| `mvn: command not found` | Maven chưa có trong PATH | Cài Maven 3.9 trở lên và mở lại terminal |
