package ro.msg.edu.jbugs.userManagement.persistence.cache;

import java.util.List;

public interface Cache {
    void add(String key, List<Object> value, long periodInMillis);

    void addValue(String key, Object value);

    void remove(String key);

    List<Object> get(String key);

    void clear();

    long size();
}
