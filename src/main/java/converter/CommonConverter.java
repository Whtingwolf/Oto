package converter;

import converter.annotation.ConvertReflect;
import converter.annotation.Reflects;

import java.util.HashMap;
import java.util.Map;

public class CommonConverter<T> {
    Map<String, Class> reflectionMap = new HashMap<>();

    public T convertTo(Object source) {
        initReflectionMap(source);
        return null;
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
