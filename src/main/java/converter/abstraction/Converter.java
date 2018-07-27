package converter.abstraction;

public interface Converter<S, T> extends ConvertTO<S, T>, ConvertFrom<S, T> {
    @Override
    S convertFrom(T source);

    @Override
    T convertTo(S source);
}
