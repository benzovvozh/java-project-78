package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {


    public final StringSchema required() {
        addCheck("required", value -> value != null && !(value.isEmpty()));
        return this;
    }

    public final StringSchema minLength(Integer minLength) {
        addCheck("minLength", value -> value != null && value.length() >= minLength);
        return this;
    }

    public final StringSchema contains(String subString) {
        boolean substringCheck;
        if (subString != null && !(subString.isEmpty())) {
            substringCheck = true;
        } else {
            substringCheck = false;
        }
        addCheck("contains", value -> value != null && substringCheck && value.contains(subString));

        return this;
    }
}
