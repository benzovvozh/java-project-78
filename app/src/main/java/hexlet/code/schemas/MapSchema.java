package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map> {
    private Integer mapSize = null;
    private boolean isRequired = false;

    @Override
    public MapSchema required() {
        this.isRequired = true;
        return this;
    }

    public MapSchema sizeof(Integer number) {
        this.mapSize = number;
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

        return true;

    }
}
