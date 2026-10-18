# Bài 01 - Đọc và ghi tệp bằng java.io: File, luồng ký tự, luồng byte, bộ đệm và bảng mã

Mã nguồn đi kèm bài học **Đọc và ghi tệp** (chương 16: Làm việc với tệp) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ dùng `File` để tạo, xóa, liệt kê và thấy các lệnh này báo lỗi bằng giá trị `boolean` thay vì ngoại lệ, ghi và đọc văn bản bằng `BufferedWriter` và `BufferedReader` (ghi đè so với ghi tiếp, `readLine` trả `null` ở cuối tệp), đo ảnh hưởng của bảng mã (`Café` thành `CafÃ©`), thấy dữ liệu nằm trong bộ đệm cho tới khi `flush` hoặc `close`, sao chép tệp nhị phân bằng luồng byte (4 lần gọi đọc so với 10001 lần), và xem `read()` trả byte dưới dạng số nguyên.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
01-java-io/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/files/io/
    Scratch.java                tạo thư mục tạm và xóa cả cây thư mục
    FileBasics.java             File: tạo, xóa, mkdir, mkdirs, liệt kê
    TextFiles.java              ghi, đọc dòng, ghi tiếp, bảng mã, bộ đệm, lỗi thường gặp
    BinaryFiles.java            sao chép luồng byte, đếm số lần đọc
    IoDemo.java                 chạy mọi ví dụ và các lỗi biên dịch
    Javac.java                  biên dịch một chuỗi mã nguồn và trả thông báo lỗi
  src/test/java/dev/canhdominich/core/files/io/
    IoTest.java                 chín test, dùng thư mục tạm của JUnit
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.files.io.IoDemo
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15, Linux), hai lần chạy liên tiếp cho cùng kết quả:

```text
delete non empty    = delete a directory that still holds a file returns false, directory still exists true, no exception was thrown
charset             = 4 characters took 5 bytes, read as UTF-8 'Café', read as ISO-8859-1 'CafÃ©'
buffering           = file length after write but before flush 0, after flush 5
binary copy         = copied 10000 bytes with a 4096 byte buffer in 4 read calls, one byte at a time took 10001 read calls, both copies identical true
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
