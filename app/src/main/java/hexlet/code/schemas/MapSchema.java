package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public class MapSchema extends BaseSchema<Map<String, Object>> {


    public MapSchema required() {
        addCheck("required", value -> value != null);
        return this;
    }

    public MapSchema sizeof(Integer number) {
        addCheck("sizeof", value -> value.size() == number);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<?>> schemas) {
        Map<String, BaseSchema<?>> shapeSchemas = new HashMap<>(schemas);
        addCheck("shape", value -> {
            if (value == null) {
                return true;
            }
            for (var entry : shapeSchemas.entrySet()) {
                var key = entry.getKey();
                BaseSchema<?> schema = entry.getValue();
                if (value.containsKey(key)) {
                    var mapValue = value.get(key);
                    if (!schema.isValid(mapValue)) {
                        return false;
                    }
                }
            }
            return true;
        });
        return this;
    }

}
