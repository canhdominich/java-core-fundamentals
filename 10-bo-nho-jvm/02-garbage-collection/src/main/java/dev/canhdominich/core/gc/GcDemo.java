package dev.canhdominich.core.gc;

import java.util.ArrayList;
import java.util.List;

public class GcDemo {

    public static List<String> collectorChoice() {
        List<String> out = new ArrayList<>();
        for (String flag : new String[] {"-XX:+UseSerialGC", "-XX:+UseParallelGC", "-XX:+UseG1GC"}) {
            List<String> lines = Child.run("dev.canhdominich.core.gc.Garbage", flag, "-Xmx64m", "-Xlog:gc");
            String using = "";
            int pauses = 0;
            for (String line : lines) {
                if (line.contains("Using ")) {
                    using = line.substring(line.indexOf("Using ")).trim();
                }
                if (line.contains("Pause Young")) {
                    pauses++;
                }
            }
            out.add(flag + " reports [" + using + "], at least one young collection happened " + (pauses > 0));
        }
        return out;
    }

    public static void main(String[] args) {
        System.out.println("strong and weak       = " + Reachability.strongAndWeak());
        System.out.println("soft versus weak      = " + Reachability.softVersusWeak());
        System.out.println("cycles                = " + Reachability.cycles());
        System.out.println("static cache          = " + Reachability.staticCacheLeak());
        System.out.println("used memory shrinks   = " + Reachability.usedMemoryShrinks());
        for (String line : collectorChoice()) {
            System.out.println("collector             = " + line);
        }
    }
}
