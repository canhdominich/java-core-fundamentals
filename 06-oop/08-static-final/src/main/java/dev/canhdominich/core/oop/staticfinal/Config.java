package dev.canhdominich.core.oop.staticfinal;

import java.util.ArrayList;
import java.util.List;

public final class Config {

    public static final int MAX_RETRIES = 3;
    public static final String SERVICE_NAME = "billing";
    public static final List<String> HOSTS = new ArrayList<>(List.of("a.example", "b.example"));
    public static final int[] PORTS = {80, 443};

    private Config() {
    }
}
