package hexlet.code.schemas;

public abstract class BaseSchema<T> {
    public abstract boolean isValid(T t);

    public abstract BaseSchema required();
}
