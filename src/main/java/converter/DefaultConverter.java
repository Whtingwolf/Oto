package converter;

import com.jd.virtual.gateway.converter.abstraction.ConvertFrom;
import com.jd.virtual.gateway.converter.abstraction.ConvertTO;
import com.jd.virtual.gateway.converter.abstraction.Converter;

public class DefaultConverter<S,T> implements Converter<S, T> {

    ConvertFrom<T, S> from;

    ConvertTO<S, T> to;

    @Override
    public S convertFrom(T source) {
        return null;
    }

    @Override
    public T convertTo(S source) {
        return null;
    }
}
