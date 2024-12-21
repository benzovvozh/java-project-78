package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;

public class StringSchema {

    private String string;
    private int minLength = 0;
    private boolean isRequired = false;
    List<String> subStrings = new ArrayList<>();


    public StringSchema required() {
        this.isRequired = true;
        return this;
    }

    public StringSchema minLength(int length) {
        this.minLength = length;
        return this;
    }

    public StringSchema contains(String subString) {
        if (subString != null && !(subString.isEmpty()))
            subStrings.add(subString);
        return this;
    }

    public boolean isValid(String string) {
        if (isRequired && (string == null || string.isEmpty())) {
            return false;
        }
        if (minLength > 0 && string.length() < minLength) {
            return false;
        }
        for (var subString : subStrings) {
            if (!(string.contains(subString))) {
                return false;
            }
        }
        return true;

    }
}
