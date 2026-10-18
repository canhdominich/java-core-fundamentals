# Bài 01 - Tổng quan OOP: lớp, đối tượng, đóng gói, kế thừa, đa hình và trừu tượng

Mã nguồn đi kèm bài học **Tổng quan OOP** (chương 6: Lập trình hướng đối tượng (OOP)) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ chạy một ví dụ nhỏ có đủ bốn ý tưởng của OOP: hai đối tượng `Account` có trạng thái riêng, lớp tự bảo vệ quy tắc của mình (đóng gói), `SavingsAccount` kế thừa `Account` và được dùng như `Account` (đa hình), và giao diện `Channel` cho phép gọi nhiều kênh gửi tin mà không biết kênh cụ thể (trừu tượng). Bạn cũng so sánh cùng bài toán viết kiểu thủ tục bằng `switch` với bản dùng giao diện, và đọc ba thông báo lỗi biên dịch bảo vệ thiết kế.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
01-tong-quan-oop/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/oop/overview/
    Account.java                lớp có trường private và các quy tắc kiểm tra
    SavingsAccount.java         lớp con cộng lãi cuối tháng
    Channel.java                giao diện của một kênh gửi tin
    EmailChannel.java           kênh email
    SmsChannel.java             kênh tin nhắn
    PushChannel.java            kênh thông báo đẩy
    ProceduralNotifier.java     cùng chức năng viết kiểu thủ tục bằng switch
    OverviewDemo.java           chạy mọi ví dụ và ba lỗi biên dịch
    Javac.java                  biên dịch một chuỗi mã nguồn và trả thông báo lỗi
  src/test/java/dev/canhdominich/core/oop/overview/
    OverviewTest.java           tám test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.oop.overview.OverviewDemo
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15):

```text
objectsAreIndependent = An 1500, Binh 1000, same object false
polymorphism          = An=0 Binh=20 Chi=50 | total interest 70
viaChannels (email)   = email to an@example.com: hi; push to an@example.com: hi; cost 1
procedural, unknown   = runtime: unknown channel: fax
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
