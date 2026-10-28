# Bài 01 - Interface là gì: hợp đồng, nhiều interface, thay thế cài đặt và lỗi biên dịch

Mã nguồn đi kèm bài học **Interface là gì?** (chương 7: Interface và lớp trừu tượng) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ chạy một đoạn mã dùng `Payable` để cộng dồn lương và hoá đơn nhà cung cấp mà không biết lớp cụ thể, một lớp thực hiện nhiều interface (`Payable` và `Exportable`), thay `SystemClock` bằng `FixedClock` để biên lai ổn định khi kiểm thử, cài đặt interface bằng lớp ẩn danh, đọc hằng số của interface, và tám thông báo lỗi biên dịch bảo vệ hợp đồng.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
01-interface/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/iface/basics/
    Payable.java                interface có hằng số, hai phương thức trừu tượng và một phương thức default
    Exportable.java             interface thứ hai
    Salary.java                 lớp thực hiện hai interface
    SupplierInvoice.java        lớp thực hiện Payable
    PaymentRun.java             cộng dồn và lọc theo Payable
    Clock.java                  interface của đồng hồ
    SystemClock.java            đồng hồ thật
    FixedClock.java             đồng hồ cố định cho kiểm thử
    Receipt.java                biên lai phụ thuộc vào Clock
    InterfaceDemo.java          chạy mọi ví dụ và tám lỗi biên dịch
    Javac.java                  biên dịch một chuỗi mã nguồn và trả thông báo lỗi
  src/test/java/dev/canhdominich/core/iface/basics/
    InterfaceTest.java          sáu test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.iface.basics.InterfaceDemo
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15):

```text
one runner, many kinds = total 3285000, over the limit: Big Co
substitutable clock    = fixed clock gives a stable receipt: paid 110 to Acme at 1700000000000; real clock starts with the same words: true
new on an interface      = [line 1: I is abstract; cannot be instantiated]
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

`lesson/` chứa các chương trình đơn lẻ mà bài học trên nền tảng dùng, mỗi file chạy độc lập bằng `java TênFile.java` (JDK 17 trở lên): `Cashier`, `CashierDemo`, `Clocked`, `Deep`, `Practice`, `Trace`, `Voucher`, `Wallet`. Thư mục con: `fails/`.
