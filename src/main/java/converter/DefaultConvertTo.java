package converter;

import com.jd.virtual.gateway.converter.abstraction.ConvertTO;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class DefaultConvertTo<S> implements ConvertTO<S, S> {

    @Override
    @SuppressWarnings("uncheck")
    public S convertTo(S source) {
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
