package converter;

import converter.annotation.ConvertReflect;
import converter.annotation.Reflects;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public abstract class CommonConverter {
    Map<String, Class> reflectionMap = new HashMap<>();

    Function DEFAULT_FUNCTION;

    public <T> T convertTo(Object source, Function<Object, T> function) {
        return function.apply(source);
    }

    public <T> T convertTo(Object source) {
        return (T) DEFAULT_FUNCTION.apply(source);
    }

    public <T> Object convertFrom(T source, Function<T, Object> function) {
        return function.apply(source);
    }

    public <T> Object convertFrom(T source) {
        return DEFAULT_FUNCTION.apply(source);
    }

    private void initReflectionMap(Object source) {
        Class clazz = source.getClass();
        Reflects annotation = (Reflects) clazz.getAnnotation(Reflects.class);
        for (ConvertReflect cr : annotation.value()) {
            if (cr.reflectName().equals("")) {

            }
            if (cr.converter().equals("defaultConverter")) {

            }
        }

    }
}
