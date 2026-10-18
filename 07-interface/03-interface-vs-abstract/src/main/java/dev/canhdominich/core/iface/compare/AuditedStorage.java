package dev.canhdominich.core.iface.compare;

import java.util.ArrayList;
import java.util.List;

public class AuditedStorage implements Storage, Auditable {

    private final Storage inner;
    private final List<String> log = new ArrayList<>();

    public AuditedStorage(Storage inner) {
        this.inner = inner;
    }

    @Override
    public void put(String key, String value) {
        log.add("put " + key);
        inner.put(key, value);
    }

    @Override
    public String get(String key) {
        log.add("get " + key);
        return inner.get(key);
    }

    @Override
    public int size() {
        return inner.size();
    }

    @Override
    public List<String> auditLog() {
        return List.copyOf(log);
    }
}
