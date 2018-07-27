package converter;

import com.jd.virtual.gateway.converter.abstraction.ConvertFrom;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class DefaultConvertFrom<S> implements ConvertFrom<S, S> {

    @Override
    @SuppressWarnings("uncheck")
    public S convertFrom(S source) {
        Constructor<S> constructor = null;
        S result = null;
        try {
            constructor = (Constructor<S>) source.getClass().getConstructor();
            result = constructor.newInstance();
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
        return result;
    }

}
