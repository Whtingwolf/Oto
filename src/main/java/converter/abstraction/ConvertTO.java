package converter.abstraction;

@FunctionalInterface
public interface ConvertTO {
    <T,R> R convertTo(T source,Class<R> productClazz);
}
