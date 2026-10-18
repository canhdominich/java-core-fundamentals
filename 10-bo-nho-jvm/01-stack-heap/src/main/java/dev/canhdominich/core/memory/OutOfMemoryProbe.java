package dev.canhdominich.core.memory;

import java.util.ArrayList;
import java.util.List;

public class OutOfMemoryProbe {

    public static void main(String[] args) {
        List<byte[]> hoard = new ArrayList<>();
        try {
            while (true) {
                hoard.add(new byte[1024 * 1024]);
            }
        } catch (OutOfMemoryError e) {
            int megabytes = hoard.size();
            hoard.clear();
            System.out.println("OutOfMemoryError message: " + e.getMessage());
            System.out.println("allocated megabytes before failing is below the 64 limit: " + (megabytes < 64) + " and above 20: " + (megabytes > 20));
        }
    }
}
