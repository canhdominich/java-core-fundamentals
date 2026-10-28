# Bài 04 - Đóng gói: trường private, getter an toàn, bốn mức truy cập

Mã nguồn đi kèm bài học **Đóng gói (Encapsulation)** (chương 6: Lập trình hướng đối tượng (OOP)) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ chạy hai phiên bản của một tài khoản (trường `public` so với trường `private` có quy tắc), thấy getter trả thẳng danh sách bên trong cho phép người gọi vượt qua giới hạn sức chứa trong khi bản trả bản sao bất biến thì không, thấy vì sao giấu cách lưu trữ bên trong (nhiệt độ lưu bằng Kelvin) giúp đổi cài đặt mà không ảnh hưởng người dùng, và đo thông báo lỗi biên dịch của bốn mức truy cập từ một gói khác.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
04-dong-goi/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/oop/encapsulation/
    LeakyAccount.java           trường public, không có quy tắc nào
    SafeAccount.java            trường private, deposit và withdraw kiểm tra điều kiện
    LeakyCourse.java            getter trả thẳng danh sách bên trong
    SafeCourse.java             getter trả bản sao không sửa được
    Temperature.java            lưu bằng Kelvin, cung cấp Celsius và Fahrenheit
    Visibility.java             bốn trường với bốn mức truy cập
    EncapsulationDemo.java      chạy mọi ví dụ và các lỗi biên dịch về truy cập
    Javac.java                  biên dịch một chuỗi mã nguồn và trả thông báo lỗi
  src/test/java/dev/canhdominich/core/oop/encapsulation/
    EncapsulationTest.java      bảy test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.oop.encapsulation.EncapsulationDemo
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15):

```text
public fields    = owner null, balance -500.0 (nothing stopped it)
leaky getter     = third enrolment accepted false, capacity 2, students [An, Binh, Chi, Dung]
safe getter      = add to the returned list: UnsupportedOperationException, students [An, Binh]
private from another package          = [line 1: privateLevel has private access in dev.canhdominich.core.oop.encapsulation.Visibility]
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

## Thư mục lesson

`lesson/` chứa các chương trình đơn lẻ mà bài học trên nền tảng dùng, mỗi file chạy độc lập bằng `java TênFile.java` (JDK 17 trở lên): `CardGuard`, `CardLimit`, `CardShop`, `Leak`, `Naked`, `SetterTrap`, `Trace`.
