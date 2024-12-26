package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {

    public final NumberSchema required() {
        addCheck("required", value -> value != null);
        return this;
    }

    public final NumberSchema positive() {
        addCheck("positive", value -> value == null || value > 0);
        return this;
    }

    public final NumberSchema range(Integer min, Integer max) {
        addCheck("range", value -> value != null && (value >= min && value <= max));
        return this;
    }
}
