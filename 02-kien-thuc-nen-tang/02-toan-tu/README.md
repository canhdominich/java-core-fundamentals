# Bài 02 - Toán tử: chia nguyên, tăng giảm, ngắn mạch, bit và thứ tự ưu tiên

Mã nguồn đi kèm bài học **Toán tử** (chương 2: Kiến thức nền tảng) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ chạy năm chương trình đo hành vi thật của toán tử: phép chia và phần dư với số âm và số 0, phép gán gộp tự ép kiểu, vì sao `i = i++` không đổi `i`, khi nào `&&` bỏ qua vế phải còn `&` thì không, ba cờ quyền dưới dạng bit và các phép dịch bit, và thứ tự ưu tiên của phép nhân, cộng, logic và ghép chuỗi.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
02-toan-tu/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/operators/
    Arithmetic.java             chia, phần dư, chia cho 0, ghép chuỗi, phép gán gộp
    IncrementTraps.java         tiền tố và hậu tố, i = i++ và hai biểu thức rối
    Logic.java                  ngắn mạch, so sánh Integer, XOR
    Bits.java                   cờ quyền dạng bit và các phép dịch
    Precedence.java             thứ tự ưu tiên, kết hợp trái phải và toán tử ba ngôi
    Javac.java                  biên dịch một chuỗi mã nguồn và trả thông báo lỗi
  src/test/java/dev/canhdominich/core/operators/
    OperatorsTest.java          mười test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.operators.Arithmetic
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.operators.IncrementTraps
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.operators.Logic
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.operators.Bits
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.operators.Precedence
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15):

```text
-7 % 3                 = -1
Math.floorMod(-7, 3)   = 2
5 / 0                  = ArithmeticException: / by zero
i = 5; i = i++     -> i=5
false && tick(true)  evaluates the right side 0 times
false &  tick(true)  evaluates the right side 1 times
-8 >>> 1             = 2147483644
2 + 3 * 4            = 14
```

Mỗi chương trình in thêm nhiều dòng; bài học trình bày đầy đủ kết quả.

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
| `mvn: command not found` | Maven chưa có trong PATH | Cài Maven 3.9 trở lên và mở lại terminal |

## Thư mục lesson

`lesson/` chứa các chương trình đơn lẻ mà bài học trên nền tảng dùng, mỗi file chạy độc lập bằng `java TênFile.java` (JDK 17 trở lên): `Breakages`, `Deeper`, `Discount`, `Exprs`, `Guard`, `Increment`, `LeapYear`, `Order`, `PriceCalc`, `Selfcheck`, `SplitBill`, `Trace`.
