package converter;

import converter.annotation.ReflectField;
import converter.ref_tool.FieldTool;
import converter.ref_tool.MethodTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Stream;

public class DefaultConverter{

    private Object source;

    private Class<?> sourceClazz;

    private Class<?> productionClazz;

    private final static Logger logger = LoggerFactory.getLogger(ConvertTo.class);

    private Map<String, Method> methodMap;

    public static DefaultConverter instance;

    public static DefaultConverter getInstance(){
        if(instance ==null){
            synchronized (DefaultConverter.class) {
                if (instance == null) {
                    instance = new DefaultConverter();
                }
            }
        }
        return instance;
    }

    private DefaultConverter(){}

    public DefaultConverter(Object source, Class<?> production) {
        this.source = source;
        this.sourceClazz = source.getClass();
        this.productionClazz = production;
    }

    public void loggerTest(){
        logger.info("info log");
        logger.debug("debug log");
        logger.error("error log");
        logger.error("Class :{} fail to instantial ",String.class.getName());
    }

    @SuppressWarnings("unchecked")
    public <T,R> R convertTo(T source,Class<R> clazz) {
        Object produceObj = null;
        this.source = source;
        sourceClazz = source.getClass();
        productionClazz = clazz;
        try {
            produceObj = clazz.newInstance();
            methodMap =  new MethodTool().getAllMethods(productionClazz);
            Field[] fields = getAnotatedField();
            initialProduction(produceObj, fields);
        } catch (InstantiationException e) {
            logger.error("Class : {} fail to instantial ",clazz.getName());
        } catch (IllegalAccessException e) {
            logger.error("Class : {} have not public no-reference constructor",clazz.getName());
        }
        return (R) produceObj;
    }

    @SuppressWarnings("unchecked")
    private <T> T initialProduction(Object produceObj, Field[] fields) {
        for (Field f : fields) {
            Method getter = getGetter(f, sourceClazz);
            ReflectField setField = f.getAnnotation(ReflectField.class);
            Method setter = null;
            try {
                setter = getSetter(getFieldAnywhere(productionClazz, setField.value()), productionClazz);
                setter.invoke(
                        produceObj,
                        getter.invoke(source)
                );
            } catch (IllegalAccessException e) {
                logger.error("the method:{} is no public ",setter.getName());
            } catch (InvocationTargetException e) {
                logger.error("the method:{} invoke fail",setter.getName());
            } catch (NoSuchFieldException e) {
                logger.error("can't not find field {}",setField.value());
            }
        }
        return (T) produceObj;
    }

    private Field getFieldAnywhere(Class clazz, String fieldName) throws NoSuchFieldException {
        Field field = null;
        Class circleClazz = clazz;
        while (field == null && circleClazz != Object.class) {
            try {
                field = circleClazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException ignored) {
            }
            circleClazz = circleClazz.getSuperclass();
        }
        if (field == null) {
            throw new NoSuchFieldException(fieldName);
        }
        return field;
    }


    public String toUpperFirstCase(String fieldName) {
        return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    private Method getGetter(Field field, Class clazz) {
        try {
            return clazz.getMethod("get" + toUpperFirstCase(field.getName()));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Method getSetter(Field field, Class clazz) {
        try {
            return clazz.getMethod("set" + toUpperFirstCase(field.getName()), field.getType());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Field[] getAnotatedField() {
        Field[] fields = new FieldTool().getAllField(sourceClazz);
        Stream<Field> stream = Stream.of(fields);
        return stream.filter((Field f) -> f.isAnnotationPresent(ReflectField.class)).toArray(Field[]::new);
    }



}
