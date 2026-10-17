# Bài 03 - Nhập xuất: print, printf, Scanner và vì sao nextLine trả chuỗi rỗng

Mã nguồn đi kèm bài học **Nhập và xuất dữ liệu** (chương 2: Kiến thức nền tảng) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

Bạn sẽ in dữ liệu bằng `print`, `println`, `printf` và đọc bảng định dạng thật, thấy định dạng số đổi theo `Locale`, tái hiện bẫy `nextInt` rồi `nextLine`, đọc thông báo của `InputMismatchException`, viết hàm nhập số có kiểm tra bằng vòng lặp, tách luồng lỗi `System.err` khỏi luồng kết quả và đo `Scanner` chậm hơn `BufferedReader` bao nhiêu lần trên một triệu số.

## Yêu cầu môi trường

| Thành phần | Phiên bản đã kiểm chứng | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Maven | 3.9 trở lên | `mvn -v` |

## Cấu trúc

```text
03-nhap-xuat/
  pom.xml                       Maven thuần, release 17, JUnit và AssertJ cho test
  src/main/java/dev/canhdominich/core/io/
    PrintDemo.java              print, println, printf, nối chuỗi và ký tự, null
    FormatTable.java            bảng định dạng printf và ảnh hưởng của Locale
    ScannerTraps.java           bẫy nextInt rồi nextLine và các ngoại lệ của Scanner
    SafeInput.java              đọc số có kiểm tra, hỏi lại cho tới khi hợp lệ
    StandardStreams.java        tách System.out và System.err bằng tiến trình con
    ReadSpeed.java              đo Scanner so với BufferedReader trên một triệu số
  src/test/java/dev/canhdominich/core/io/
    IoTest.java                 mười test
```

## Chạy thí nghiệm

```bash
mvn -q compile exec:exec -Dmain.class=dev.canhdominich.core.io.PrintDemo
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.io.FormatTable
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.io.ScannerTraps
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.io.StandardStreams
mvn -q exec:exec -Dmain.class=dev.canhdominich.core.io.ReadSpeed
printf 'abc\n200\n30\nAn Binh\n' | mvn -q exec:exec -Dmain.class=dev.canhdominich.core.io.SafeInput
```

Dòng cuối đưa dữ liệu vào chương trình bằng ống (`|`) thay vì gõ bàn phím.

## Kết quả mong đợi

Trên máy đã kiểm chứng (JDK 17.0.15):

```text
naive  (nextInt then nextLine): age=25, name=[]
fixed  (nextLine then parse)  : age=25, name=[An Binh]
Locale.GERMANY 1.234.567,89
printf("%d", 3.5) -> IllegalFormatConversionException: d != java.lang.Double
stdout captured: result: 42|result: 43|
stderr captured: warning: something odd|
Scanner is about 12 times slower
```

Tỉ lệ `Scanner` chậm hơn `BufferedReader` phụ thuộc máy, nhưng luôn là nhiều lần.

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
| `SafeInput` không đọc được dữ liệu từ ống | Chạy bằng `mvn` không có `-q` làm lẫn dòng log | Thêm `-q` hoặc chạy trực tiếp bằng `java -cp target/classes ...` |
| Số in ra dùng dấu phẩy thập phân | Locale của hệ điều hành là tiếng Việt hoặc Đức | Truyền `Locale.ROOT` hoặc `Locale.US` vào `String.format` |
