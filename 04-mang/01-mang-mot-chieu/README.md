# Bài 01 - Mảng một chiều: khai báo, giá trị mặc định, duyệt, sao chép và lỗi thường gặp

Mã nguồn đi kèm bài học **Mảng một chiều** (chương 4: Mảng) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ chạy các thí nghiệm để thấy giá trị mặc định của từng kiểu mảng, ba lỗi chỉ số và kích thước (`ArrayIndexOutOfBoundsException`, `NegativeArraySizeException`, `OutOfMemoryError`), vì sao in thẳng một mảng ra `[I@...`, khác biệt giữa gán mảng, `clone`, `Arrays.copyOf` và `System.arraycopy`, bẫy sao chép nông với mảng đối tượng, các phép duyệt thường gặp (tổng, lớn nhất, đảo, xoay, tổng dồn, đếm chữ cái) và sáu lỗi biên dịch về mảng.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
01-mang-mot-chieu/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/arrays/
    ArrayBasics.java            giá trị mặc định, chỉ số ngoài khoảng, kích thước âm, null, tràn bộ nhớ
    ArrayCopies.java            gán, clone, copyOf, arraycopy, sao chép nông và sâu
    ArrayTraversal.java         tổng, lớn nhất, tìm kiếm, đảo, xoay, tổng dồn, đếm chữ cái
    ArrayErrors.java            sáu lỗi biên dịch về mảng
    Javac.java                  biên dịch một chuỗi mã nguồn và trả thông báo lỗi
  src/test/java/dev/canhdominich/core/arrays/
    ArraysTest.java             mười lăm test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.arrays.ArrayBasics
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.arrays.ArrayCopies
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.arrays.ArrayTraversal
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.arrays.ArrayErrors
```

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15):

```text
outOfBounds(3)          = ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3
negativeSize(-1)        = NegativeArraySizeException: -1
tooBig()                = OutOfMemoryError: Requested array size exceeds VM limit
alias               = a = [99, 2, 3], same array: true
shallow copy        = original values 99, 2 | copy values 99, 50 | same first element: true
```

## Chạy bộ test

```bash
mvn test
```

Kết quả mong đợi: `Tests run: 15, Failures: 0, Errors: 0`.

## Dọn dẹp

```bash
mvn clean
```

## Lỗi thường gặp khi chạy lab

| Triệu chứng | Nguyên nhân | Cách xử lý |
|---|---|---|
| `mvn: command not found` | Maven chưa có trong PATH | Cài Maven 3.9 trở lên và mở lại terminal |

## Thư mục lesson

`lesson/` chứa các chương trình đơn lẻ mà bài học trên nền tảng dùng, mỗi file chạy độc lập bằng `java TênFile.java` (JDK 17 trở lên): `AliasDemo`, `Breakages`, `ClassScores`, `ScoreBoard`, `Selfcheck`, `Shallow`.
