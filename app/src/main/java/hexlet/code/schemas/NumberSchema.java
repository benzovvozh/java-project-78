package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {
    private boolean isRequired = false;
    private boolean isPositive = false;
    private Integer max = null;
    private Integer min = null;

    @Override
    public NumberSchema required() {
        this.isRequired = true;
        return this;
    }

    public NumberSchema positive() {
        this.isPositive = true;
        return this;
    }

    public NumberSchema range(Integer min, Integer max) {
        this.min = min;
        this.max = max;
        return this;
    }

    @Override
    public boolean isValid(Integer number) {
        if (isRequired && number == null) {
            return false;
        }
        if (number != null && min != null && max != null) {
            if (number < min || number > max) {
                return false;
            }

        }
        if (number != null)
            if (isPositive && number <= 0) {
                return false;
            }
        return true;
    }

}
