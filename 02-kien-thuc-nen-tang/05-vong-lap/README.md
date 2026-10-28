# Bài 05 - Vòng lặp: for, while, do-while, break, continue và các bẫy

Mã nguồn đi kèm bài học **Vòng lặp** (chương 2: Kiến thức nền tảng) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ chạy ba dạng vòng lặp và thấy chỗ kiểm tra điều kiện quyết định số lần chạy, so sánh `for-each` với `for` theo chỉ số khi cần sửa phần tử, tái hiện các bẫy (sai lệch một đơn vị, dấu chấm phẩy thừa, bộ đếm số thực, bộ đếm `byte`, danh sách co lại khi đang lặp), dùng `break`, `continue` và nhãn để thoát vòng lặp ngoài, rồi làm các bài mẫu (FizzBuzz, tổng chữ số, đảo số, dãy Collatz, tìm kiếm tuyến tính và nhị phân).

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
05-vong-lap/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/loops/
    LoopForms.java              for, while, do-while, hai biến trong tiêu đề, for-each
    LoopPitfalls.java           sai lệch một đơn vị, dấu chấm phẩy thừa, bộ đếm số thực và byte, lỗi biên dịch
    BreakContinue.java          break, continue, nhãn, số nguyên tố
    Practice.java               FizzBuzz, tổng và đảo chữ số, Collatz, tìm kiếm tuyến tính và nhị phân
    Javac.java                  biên dịch một chuỗi mã nguồn và trả thông báo lỗi
  src/test/java/dev/canhdominich/core/loops/
    LoopsTest.java              mười bốn test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.loops.LoopForms
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.loops.LoopPitfalls
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.loops.BreakContinue
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.loops.Practice
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15):

```text
while starting at 10     = 0 runs
do-while starting at 10  = 1 runs
offByOne                 = total so far 18, then ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3
iterations, plain break  = 8
iterations, labeled break= 5
collatzSteps(6, 27)      = 8, 111
search for 1998 in 1000 numbers: linear 1000 steps, binary 10 steps
```

## Chạy bộ test

```bash
mvn test
```

Kết quả mong đợi: `Tests run: 14, Failures: 0, Errors: 0`.

## Dọn dẹp

```bash
mvn clean
```

## Lỗi thường gặp khi chạy lab

| Triệu chứng | Nguyên nhân | Cách xử lý |
|---|---|---|
| `mvn: command not found` | Maven chưa có trong PATH | Cài Maven 3.9 trở lên và mở lại terminal |

## Thư mục lesson

`lesson/` chứa các chương trình đơn lẻ mà bài học trên nền tảng dùng, mỗi file chạy độc lập bằng `java TênFile.java` (JDK 17 trở lên): `BreakContinue`, `Breakages`, `CartDesk`, `CartSum`, `Countdown`, `Deeper`, `ForEach`, `Forms`, `Selfcheck`, `StockReport`, `Table`, `Trace`.
