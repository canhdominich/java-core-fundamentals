# Bài 02 - java.nio.file: Path, Files, đọc ghi nhanh, luồng dòng lười, duyệt cây thư mục và chống path traversal

Mã nguồn đi kèm bài học **NIO và thao tác tệp hiện đại** (chương 16: Làm việc với tệp) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ dựng `Path` bằng `resolve`, `relativize`, `normalize`, thấy kiểm tra `startsWith` trước khi `normalize` để lọt đường dẫn thoát khỏi thư mục gốc, đọc và ghi cả tệp bằng `Files.readString` và `writeString` với các tùy chọn `CREATE_NEW` và `APPEND`, thấy `Files` ném ngoại lệ cụ thể (`NoSuchFileException`, `FileAlreadyExistsException`, `DirectoryNotEmptyException`, `MalformedInputException`), đọc tệp 100000 dòng bằng `Files.lines` lười, duyệt cây bằng `Files.walk`, sao chép và di chuyển với `REPLACE_EXISTING`, và thay tệp cấu hình bằng `ATOMIC_MOVE`.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
02-java-nio-file/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/files/nio/
    Scratch.java                tạo thư mục tạm và xóa cả cây thư mục
    PathOps.java                Path: thành phần, resolve, relativize, normalize, path traversal
    Quick.java                  đọc ghi nhanh, tùy chọn mở tệp, lỗi cụ thể, luồng dòng lười
    Tree.java                   thư mục, copy, move, delete, walk, thay tệp nguyên tử
    NioDemo.java                chạy mọi ví dụ và các lỗi biên dịch
    Javac.java                  biên dịch một chuỗi mã nguồn và trả thông báo lỗi
  src/test/java/dev/canhdominich/core/files/nio/
    NioTest.java                chín test, dùng thư mục tạm của JUnit
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.files.nio.NioDemo
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15, Linux), hai lần chạy liên tiếp cho cùng kết quả:

```text
traversal           = report.pdf inside true, ../../etc/passwd inside false, /etc/passwd inside false, naive startsWith on the raw text true
bad utf8            = readString threw MalformedInputException: Input length = 1, decoding the same bytes by hand gives 'Caf?'
lazy lines          = 100000 lines, ERROR lines 4, first match 'ERROR failure number 25000' found after reading 25000 lines
delete              = delete on a missing file threw NoSuchFileException, deleteIfExists returned false, delete on a non-empty directory threw DirectoryNotEmptyException
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
