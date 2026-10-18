# Bài 04 - Đệ quy và phương thức static so với phương thức thể hiện

Mã nguồn đi kèm bài học **Đệ quy và static vs instance** (chương 3: Phương thức) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ chạy đệ quy giai thừa và thấy điều kiện dừng quyết định mọi thứ, đo vì sao Fibonacci đệ quy đơn giản gọi hơn hai triệu lần và cách ghi nhớ kết quả (memoization) giảm xuống 59 lần, so sánh luỹ thừa tuyến tính với luỹ thừa chia đôi, gây `StackOverflowError` có chủ đích và đo độ sâu với hai kích thước ngăn xếp. Phần thứ hai so sánh thành viên `static` (một bản cho cả lớp) với thành viên thể hiện (mỗi đối tượng một bản) và đọc các lỗi biên dịch của ngữ cảnh `static`.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
04-de-quy-static/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/recursion/
    Recursion.java              giai thừa, Fibonacci, luỹ thừa, tháp Hà Nội, tràn ngăn xếp và đo độ sâu
    Counter.java                lớp có trường static dùng chung và trường thể hiện riêng
    StaticVsInstance.java       so sánh hai loại thành viên và năm lỗi biên dịch
    Javac.java                  biên dịch một chuỗi mã nguồn và trả thông báo lỗi
  src/test/java/dev/canhdominich/core/recursion/
    RecursionTest.java          mười test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.recursion.Recursion
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.recursion.StaticVsInstance
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15), các con số cố định:

```text
factorial(21) wraps around          = -4249290049419214848
fib(30) naive                       = 832040 in 2692537 calls
fib(30) memoized                    = 832040 in 59 calls
2 to the 30th, halving recursion    = 1073741824 in 6 calls
hanoiMoves(3), (10), (20)           = 7, 1023, 1048575
Counter.created()     = 3
```

Hai dòng độ sâu ngăn xếp thay đổi từ lần chạy này sang lần chạy khác (khoảng vài nghìn khung với ngăn xếp 256 KB và vài triệu khung với 64 MB); chỉ có thứ tự lớn nhỏ là cố định.

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
