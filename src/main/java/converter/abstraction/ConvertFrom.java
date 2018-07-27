package converter.abstraction;

@FunctionalInterface
public interface ConvertFrom<T, R> {
    T convertFrom(R source);
}
