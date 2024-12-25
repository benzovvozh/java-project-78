package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected Map<String, Predicate<T>> checks = new LinkedHashMap<>();

    protected final void addCheck(String key, Predicate<T> value) {
        this.checks.put(key, value);
    }

    public final boolean isValid(Object value) {
        for (var entry : checks.entrySet()) {
            var predicate = entry.getValue();
            if (!(predicate.test((T) value))) {
                return false;
            }
        }
        return true;
    }
}
