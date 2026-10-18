# Bài 01 - Thread và vòng đời: tạo luồng, sáu trạng thái, join, interrupt và ngoại lệ không bắt

Mã nguồn đi kèm bài học **Thread và vòng đời** (chương 15: Đa luồng) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ tạo luồng bằng lớp con `Thread` và `Runnable`, thấy `run()` chạy trên luồng gọi còn `start()` chạy trên luồng mới, thấy gọi `start()` lần hai ném `IllegalThreadStateException`, quan sát cả sáu trạng thái `NEW`, `RUNNABLE`, `TIMED_WAITING`, `WAITING`, `BLOCKED`, `TERMINATED` trên một luồng, dùng `join` để thấy kết quả, dùng `interrupt` để đánh thức luồng đang ngủ và dừng vòng lặp hợp tác, và thấy ngoại lệ không bắt chỉ giết luồng của nó.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
01-thread-vong-doi/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/threads/lifecycle/
    Creating.java               ba cách tạo luồng, start so với run, start hai lần, luồng nền
    States.java                 đưa một luồng đi qua sáu trạng thái
    Stopping.java               join, interrupt, dừng hợp tác, ngoại lệ không bắt
    LifecycleDemo.java          chạy mọi ví dụ và các lỗi biên dịch
    Javac.java                  biên dịch một chuỗi mã nguồn và trả thông báo lỗi
  src/test/java/dev/canhdominich/core/threads/lifecycle/
    LifecycleTest.java          chín test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.threads.lifecycle.LifecycleDemo
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15), hai lần chạy liên tiếp cho cùng kết quả:

```text
start vs run        = run() executed on main, start() executed on worker-1
states              = [NEW, RUNNABLE, TIMED_WAITING, WAITING, BLOCKED, TERMINATED]
interrupt sleeper   = caught InterruptedException with message 'sleep interrupted', interrupt flag inside catch false, state TERMINATED
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
