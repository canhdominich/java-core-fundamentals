package dev.canhdominich.core.intro;

import java.nio.ByteBuffer;

public record ClassFileHeader(int magic, int minor, int major) {

    public static ClassFileHeader parse(byte[] classFile) {
        ByteBuffer buffer = ByteBuffer.wrap(classFile);
        return new ClassFileHeader(buffer.getInt(), buffer.getShort() & 0xFFFF, buffer.getShort() & 0xFFFF);
    }

    public int javaVersion() {
        return major - 44;
    }

    public String magicHex() {
        return Integer.toHexString(magic).toUpperCase();
    }

    public static byte[] withMajor(byte[] classFile, int major) {
        byte[] copy = classFile.clone();
        copy[6] = (byte) (major >> 8);
        copy[7] = (byte) major;
        return copy;
    }
}
