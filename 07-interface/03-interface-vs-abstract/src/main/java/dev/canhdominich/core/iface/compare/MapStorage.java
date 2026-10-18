package dev.canhdominich.core.iface.compare;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private final Map<String, String> data = new HashMap<>();

    @Override
    protected void doPut(String key, String value) {
        data.put(key, value);
    }

    @Override
    public String get(String key) {
        return data.get(key);
    }

    @Override
    public int size() {
        return data.size();
    }
}
