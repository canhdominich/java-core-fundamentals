package dev.canhdominich.core.files.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public final class BinaryFiles {

    private BinaryFiles() {
    }

    static final class CountingInputStream extends InputStream {
        private final InputStream inner;
        int calls;

        CountingInputStream(InputStream inner) {
            this.inner = inner;
        }

        @Override
        public int read() throws IOException {
            calls++;
            return inner.read();
        }

        @Override
        public int read(byte[] b, int off, int len) throws IOException {
            calls++;
            return inner.read(b, off, len);
        }

        @Override
        public void close() throws IOException {
            inner.close();
        }
    }

    public static byte[] sample(int size) {
        byte[] data = new byte[size];
        for (int i = 0; i < size; i++) {
            data[i] = (byte) (i * 31 + 7);
        }
        return data;
    }

    public static long copy(InputStream in, File target, int bufferSize) throws IOException {
        long total = 0;
        try (FileOutputStream out = new FileOutputStream(target)) {
            byte[] buffer = new byte[bufferSize];
            int n;
            while ((n = in.read(buffer)) != -1) {
                out.write(buffer, 0, n);
                total += n;
            }
        }
        return total;
    }

    public static String copyBlocksVersusBytes(File dir) throws IOException {
        File source = new File(dir, "source.bin");
        byte[] data = sample(10_000);
        try (FileOutputStream out = new FileOutputStream(source)) {
            out.write(data);
        }
        File blockCopy = new File(dir, "block.bin");
        CountingInputStream blocks = new CountingInputStream(new FileInputStream(source));
        long copied;
        try (blocks) {
            copied = copy(blocks, blockCopy, 4096);
        }
        int byteCalls;
        File byteCopy = new File(dir, "byte.bin");
        CountingInputStream singles = new CountingInputStream(new FileInputStream(source));
        try (singles; FileOutputStream out = new FileOutputStream(byteCopy)) {
            int b;
            while ((b = singles.read()) != -1) {
                out.write(b);
            }
        }
        byteCalls = singles.calls;
        boolean same = Arrays.equals(data, java.nio.file.Files.readAllBytes(blockCopy.toPath())) && Arrays.equals(data, java.nio.file.Files.readAllBytes(byteCopy.toPath()));
        return "copied " + copied + " bytes with a 4096 byte buffer in " + blocks.calls + " read calls, one byte at a time took " + byteCalls + " read calls, both copies identical " + same;
    }

    public static String readReturnsByteAsInt() throws IOException {
        byte negative = (byte) 200;
        java.io.ByteArrayInputStream in = new java.io.ByteArrayInputStream(new byte[] {negative});
        int fromStream = in.read();
        return "the byte value as Java sees it " + negative + ", what read() returns " + fromStream + ", end of data " + in.read();
    }
}
