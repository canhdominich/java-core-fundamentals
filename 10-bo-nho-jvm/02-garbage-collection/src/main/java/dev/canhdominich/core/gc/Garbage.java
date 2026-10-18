package dev.canhdominich.core.gc;

public class Garbage {

    public static void main(String[] args) {
        long checksum = 0;
        for (int i = 0; i < 400; i++) {
            byte[] chunk = new byte[1024 * 1024];
            chunk[0] = (byte) i;
            checksum += chunk[0];
        }
        System.out.println("allocated 400 MB of short-lived arrays, checksum " + checksum);
    }
}
