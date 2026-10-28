#!/bin/bash
# Thí nghiệm: hằng số biên dịch được chép vào lớp dùng nó (JLS 13.4.9). Cần JDK 17, không cần lệnh javac riêng.
set -e
HERE="$(cd "$(dirname "$0")" && pwd)"
W="$(mktemp -d)"
JAVAC="java -m jdk.compiler/com.sun.tools.javac.Main"
JAVAP="java -m jdk.jdeps/com.sun.tools.javap.Main"
cp "$HERE/Config.java" "$HERE/Checkout.java" "$W/"
cd "$W"

echo "== Bước 1: biên dịch cả hai lớp, chạy"
$JAVAC Config.java Checkout.java
java Checkout

echo "== Bước 2: đổi 100 thành 200 trong Config.java, chỉ biên dịch lại Config"
sed -i 's/MAX_ITEMS = 100/MAX_ITEMS = 200/; s/parseInt("100")/parseInt("200")/' Config.java
grep -n "MAX" Config.java
$JAVAC Config.java
java Checkout

echo "== Bước 3: javap -c trên Checkout.class (chỉ các lệnh liên quan)"
$JAVAP -c -p Checkout.class | grep -E "bipush|sipush|getstatic" | grep -v "System.out"

echo "== Bước 4: javap -v trên Config.class"
$JAVAP -v -p Config.class | grep -E "ConstantValue:|static final int"

echo "== Bước 5: biên dịch lại cả Checkout, chạy"
$JAVAC Checkout.java
java Checkout
echo "(thư mục tạm: $W)"
