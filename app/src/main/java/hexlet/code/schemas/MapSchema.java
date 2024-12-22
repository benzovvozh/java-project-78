package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map> {
    private Integer mapSize = null;
    private boolean isRequired = false;
    private Map<String, BaseSchema<String>> schemas;

    @Override
    public MapSchema required() {
        this.isRequired = true;
        return this;
    }

    public MapSchema sizeof(Integer number) {
        this.mapSize = number;
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> schemas1) {
        this.schemas = schemas1;
        return this;
    }

    @Override
    public boolean isValid(Map map) {
        if (isRequired && map == null) {
            return false;
        }
        if (mapSize != null) {
            if (map.size() != mapSize) {
                return false;
            }
        }
        var keys = map.keySet();
        for (var key : keys) {
            if (map.containsKey(key) && schemas.containsKey(key)) {
                var schema = schemas.get(key);
                var value = map.get(key);

                if (!schema.isValid((String) value)) {
                    return false;
                }
            }
        }
        return true;

    }
}
