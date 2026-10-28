# Bài 04 - Câu lệnh điều kiện: if, switch cổ điển, switch biểu thức và các bẫy

Mã nguồn đi kèm bài học **Câu lệnh điều kiện** (chương 2: Kiến thức nền tảng) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ chạy chuỗi `if` xếp loại và thấy thứ tự điều kiện quyết định kết quả, tái hiện `else` dính vào `if` gần nhất, điều kiện gán `flag = true` và so sánh chuỗi bằng `==`, rồi xem `switch` cổ điển chạy xuyên các nhãn khi thiếu `break`, `switch` trên chuỗi và trên `null`, `switch` biểu thức với `->`, nhiều nhãn và `yield`, và bốn lỗi biên dịch của `switch` biểu thức. Cuối bài là hàm tính phí giao hàng kết hợp cả hai.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
04-cau-lenh-dieu-kien/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/conditions/
    IfPitfalls.java             xếp loại bằng if, thứ tự điều kiện, else treo, gán trong điều kiện
    OldSwitch.java              switch cổ điển: xuyên nhãn, gom nhãn, chuỗi, null
    Day.java                    enum bảy ngày trong tuần
    SwitchExpressions.java      switch biểu thức, yield, tính đầy đủ, bốn lỗi biên dịch
    Shipping.java               tính phí giao hàng bằng switch biểu thức và if
    Javac.java                  biên dịch một chuỗi mã nguồn và trả thông báo lỗi
  src/test/java/dev/canhdominich/core/conditions/
    ConditionsTest.java         chín test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.conditions.IfPitfalls
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.conditions.OldSwitch
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.conditions.SwitchExpressions
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.conditions.Shipping
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15):

```text
withoutBreak(1)   = [Monday, Tuesday, Wednesday]
withoutBreak(3)   = [Wednesday]
onText(null)       = NullPointerException: Cannot invoke "String.hashCode()" because "<local1>" is null
expression, no default: [line 1: the switch expression does not cover all possible input values]
fee(CT, 6.0, member)     = 45000
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

`lesson/` chứa các chương trình đơn lẻ mà bài học trên nền tảng dùng, mỗi file chạy độc lập bằng `java TênFile.java` (JDK 17 trở lên): `Braces`, `Breakages`, `Command`, `Dangling`, `Deeper`, `Fallthrough`, `Selfcheck`, `Shipping`, `Tier`, `TierDesk`, `Trace`, `WrongTier`, `ZoneFee`.
