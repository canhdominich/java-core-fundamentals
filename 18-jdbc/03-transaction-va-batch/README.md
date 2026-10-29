# Bài 03 - Transaction và Batch

Mã nguồn đi kèm bài học **Transaction và Batch** (chương 18: Kết nối cơ sở dữ liệu JDBC) của khóa Java: Nền tảng lập trình, OOP, Collections và JDBC.

## Yêu cầu môi trường

| Thành phần | Phiên bản | Ghi chú |
|---|---|---|
| JDK | 17 trở lên | `java -version` |
| Docker | bản gần đây | chạy MySQL 8.4 bằng `docker compose` |
| MySQL Connector/J | 26.7.0 | tải jar ở phần dưới |

## Cách chạy

Mỗi file trong `lesson/` là một chương trình đơn lẻ, chạy thẳng từ mã nguồn, không cần Maven hay biên dịch riêng.

```bash
cd lesson
java -cp mysql-connector-j-26.7.0.jar TênFile.java
```

## Chuẩn bị MySQL

Tải driver và bật MySQL (file `docker-compose.yml` nằm ở thư mục `18-jdbc/`, cổng 3307, database `shop`, user `app`, mật khẩu `app_pw`):

```bash
curl -fLO https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/26.7.0/mysql-connector-j-26.7.0.jar
cd .. && docker compose up -d && cd -
```

Chạy `bash reset.sh` trong `lesson/` để đặt lại các bảng về dữ liệu mẫu trước mỗi lần thử.

## Các file trong lesson/

- `BatchInsert.java`
- `BatchTimer.java`
- `ErrorTour.java`
- `ImportProducts.java`
- `NoTx.java`
- `Shop.java`
- `Transfer.java`
- `Wallet.java`
- `reset.sh`

Mỗi chương trình được giải thích từng dòng trong bài học tương ứng trên nền tảng.
