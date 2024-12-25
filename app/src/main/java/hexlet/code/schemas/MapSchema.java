package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map> {


    public MapSchema required() {
        addCheck("required", value -> value != null);
        return this;
    }

    public MapSchema sizeof(Integer number) {
        addCheck("sizeof", value -> value.size() == number);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<?>> schemas) {

        addCheck("shape", value -> {
            if (value == null || schemas == null) {
                return false;
            }
            for (var entry : schemas.entrySet()) {
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
