package converter.parse;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class FieldProcessor {
    private Map<String, Object> fieldsReflection;

    public Map<String, Object> getAllFields(Class<?> POJO) {
        Field[] fields = POJO.getDeclaredFields();
        fieldsReflection = new HashMap<>();
        for (Field f : fields) {
            fieldsReflection.put(f.getName(), "");
        }
        return fieldsReflection;
    }

    public Map<String, Object> getAllFields(Object POJO) {
        return getAllFields(POJO.getClass());
    }

    public Map<String, Object> getAllFields(String POJO) throws ClassNotFoundException {
        return getAllFields(Class.forName(POJO));
    }

    public Map<String, Object> getFieldValues(Object POJO, Class<?> clazz) {
        fieldsReflection = getAllFields(POJO);
        for (Map.Entry<String, Object> entry : fieldsReflection.entrySet()) {
            try {
                Method method = clazz.getMethod(getFieldGetMethod(entry.getKey()));
                fieldsReflection.put(entry.getKey(), method.invoke(POJO));
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return fieldsReflection;
    }

    @SuppressWarnings("uncheck")
    public <T> T setFieldValues(Object POJO, Class<T> clazz, String fieldName) {
        T toObject = null;
        try {
            toObject = clazz.getConstructor().newInstance();
            if (POJO.getClass().equals(clazz)) {
                try {
                    Method method = clazz.getMethod(getFieldSetMethod(fieldName));
                    method.invoke(toObject, POJO);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return toObject;
    }

    public boolean setFieldValues(Object POJO, Field field) {
        return setFieldValues(POJO, field.getType(), field.getName()) != null;
    }

    public Map<String, Object> getFieldValues(Object POJO) {
        return getFieldValues(POJO, POJO.getClass());
    }

    private String getFieldGetMethod(String fieldname) {
        return "get" + fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1);
    }

    private String getFieldSetMethod(String fieldname) {
        return "set" + fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1);
    }


}
