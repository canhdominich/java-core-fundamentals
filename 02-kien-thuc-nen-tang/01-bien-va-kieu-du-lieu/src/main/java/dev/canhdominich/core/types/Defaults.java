package dev.canhdominich.core.types;

public class Defaults {

    byte aByte;
    short aShort;
    int anInt;
    long aLong;
    float aFloat;
    double aDouble;
    char aChar;
    boolean aBoolean;
    String aString;
    int[] anArray;

    public String describe() {
        return "byte=" + aByte + " short=" + aShort + " int=" + anInt + " long=" + aLong + " float=" + aFloat + " double=" + aDouble
                + " char=\\u" + String.format("%04x", (int) aChar) + " boolean=" + aBoolean + " String=" + aString + " int[]=" + anArray;
    }
}
