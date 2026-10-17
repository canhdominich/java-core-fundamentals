package dev.canhdominich.core.types;

public class PrimitiveTable {

    public static void main(String[] args) {
        System.out.printf("%-8s %5s  %s%n", "type", "bits", "range");
        System.out.printf("%-8s %5d  %d .. %d%n", "byte", Byte.SIZE, Byte.MIN_VALUE, Byte.MAX_VALUE);
        System.out.printf("%-8s %5d  %d .. %d%n", "short", Short.SIZE, Short.MIN_VALUE, Short.MAX_VALUE);
        System.out.printf("%-8s %5d  %d .. %d%n", "int", Integer.SIZE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.printf("%-8s %5d  %d .. %d%n", "long", Long.SIZE, Long.MIN_VALUE, Long.MAX_VALUE);
        System.out.printf("%-8s %5d  %s .. %s%n", "float", Float.SIZE, -Float.MAX_VALUE, Float.MAX_VALUE);
        System.out.printf("%-8s %5d  %s .. %s%n", "double", Double.SIZE, -Double.MAX_VALUE, Double.MAX_VALUE);
        System.out.printf("%-8s %5d  %d .. %d%n", "char", Character.SIZE, (int) Character.MIN_VALUE, (int) Character.MAX_VALUE);
        System.out.printf("%-8s %5s  %s%n", "boolean", "n/a", "true or false (size not defined by the language)");
        System.out.println("defaults of fields: " + new Defaults().describe());
    }
}
