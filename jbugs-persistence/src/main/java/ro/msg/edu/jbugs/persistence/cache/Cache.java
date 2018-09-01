package ro.msg.edu.jbugs.persistence.cache;

import java.util.List;

public interface Cache {
    void add(String key, List<Object> value, long periodInMillis);

    void addValue(String key, Object value);

    void removeCachedValues(String key);

    List<Object> getCachedValues(String key);

    void clear();

    long size();
}
