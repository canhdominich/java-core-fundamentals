# Bài 03 - throw, throws và ngoại lệ tự định nghĩa: dữ liệu, chuỗi nguyên nhân và ném lại

Mã nguồn đi kèm bài học **throw, throws và ngoại lệ tùy chỉnh** (chương 8: Xử lý ngoại lệ) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ tự định nghĩa một ngoại lệ checked mang dữ liệu (`InsufficientFundsException` với số thiếu hụt) và một ngoại lệ unchecked cho lỗi lập trình (`InvalidAmountException`), xây chuỗi nguyên nhân qua ba tầng (kho dữ liệu, dịch vụ, người gọi) và in được toàn bộ chuỗi, xem dòng `Caused by` trong vết ngăn xếp, so sánh ba cách ném lại (cùng đối tượng, đối tượng mới không nguyên nhân, đối tượng mới có nguyên nhân) qua khung trên cùng của vết, và đọc sáu thông báo lỗi biên dịch về `throws`.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
03-throw-throws-tuy-chinh/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/exceptions/custom/
    InsufficientFundsException.java ngoại lệ checked mang dữ liệu thiếu hụt
    InvalidAmountException.java     ngoại lệ unchecked cho đối số sai
    Wallet.java                     phương thức khai báo throws và tự ném
    StorageException.java           ngoại lệ checked của tầng kho dữ liệu, có nguyên nhân
    ServiceException.java           ngoại lệ unchecked của tầng dịch vụ, có nguyên nhân
    UserRepository.java             dịch lỗi định dạng số thành StorageException
    UserService.java                dịch StorageException thành ServiceException
    Rethrow.java                    ba cách ném lại và khung trên cùng của vết
    CustomDemo.java                 chạy mọi ví dụ và sáu lỗi biên dịch
    Javac.java                      biên dịch một chuỗi mã nguồn và trả thông báo lỗi
  src/test/java/dev/canhdominich/core/exceptions/custom/
    CustomTest.java                 bảy test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.exceptions.custom.CustomDemo
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15):

```text
checked exception     = caught: need 250 but only 100 available, shortfall 150, balance still 100
not a number          = ServiceException [cannot describe user abc] caused by StorageException [id column is not a number: abc] caused by NumberFormatException [For input string: "abc"]
rethrow               = rethrown as is: top frame is lowLevel
new exception + cause = wrapped with a cause: top frame is wrapWithCause, cause frame is lowLevel
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

`lesson/` chứa các chương trình đơn lẻ mà bài học trên nền tảng dùng, mỗi file chạy độc lập bằng `java TênFile.java` (JDK 17 trở lên): `CauseTrace`, `Deep`, `Intake`, `Practice`, `Selfcheck`, `StockDemo`, `Trace`, `WrapDemo`, `WrapLost`. Thư mục con: `fails/`.
