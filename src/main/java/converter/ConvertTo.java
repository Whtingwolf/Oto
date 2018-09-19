package converter;

import java.util.function.Function;

public interface ConvertTo {

    <T,R> R convertTo(T source, Class<R> production, Function<T, R> function);

    @SuppressWarnings("unchecked")
    default <T,R> R convertTo(T source, Class<R> production){
        DefaultConverter defaultConverter = new DefaultConverter(source,production);
        return (R)defaultConverter.convertTo(source,production);
    }

}
