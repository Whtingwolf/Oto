package converter;

import converter.abstraction.ConvertFrom;
import converter.abstraction.ConvertTO;
import converter.annotation.ReflectField;
import converter.ref_tool.FieldTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Stream;

public class DefaultConverter implements ConvertTO, ConvertFrom {

    private Object source;

    private Class<?> sourceClazz;

    private Class<?> productionClazz;

    private final static Logger logger = LoggerFactory.getLogger(DefaultConverter.class);

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


    @Override
    @SuppressWarnings("unchecked")
    public <T,R> R convertTo(T source,Class<R> productClazz) {
        Object productObj = null;
        this.source = source;
        sourceClazz = source.getClass();
        productionClazz = productClazz;
        try {
            productObj = productClazz.newInstance();
            Field[] fields = getAnotatedField(sourceClazz);
            initialProduction(productObj, fields);
        } catch (InstantiationException e) {
            logger.error("Class : {} fail to instantial ",productClazz.getName());
        } catch (IllegalAccessException e) {
            logger.error("Class : {} have not public no-reference constructor",productClazz.getName());
        }
        return (R) productObj;
    }

    @Override
    public <T,R> T convertFrom(R source, Class<T> productClazz) {
        Object productObj = null;
        this.source = source;
        sourceClazz = source.getClass();
        productionClazz = productClazz;
        try{
            productObj = productClazz.newInstance();
            Field[] fields = getAnotatedField(productionClazz);

        }catch (InstantiationException e) {
            logger.error("Class : {} fail to instantial ",productClazz.getName());
        } catch (IllegalAccessException e) {
            logger.error("Class : {} have not public no-reference constructor",productClazz.getName());
        }
        return (T) productObj;
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

    private Field[] getAnotatedField(Class anooatedClass) {
        Field[] fields = new FieldTool().getAllField(sourceClazz);
        Stream<Field> stream = Stream.of(fields);
        return stream.filter((Field f) -> f.isAnnotationPresent(ReflectField.class)).toArray(Field[]::new);
    }


}
