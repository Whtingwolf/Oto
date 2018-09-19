package converter;

import converter.annotation.ReflectField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Stream;

public class DefaultConverter{

    private Object source;

    private Class<?> sourceClazz;

    private Class<?> productionClazz;

    private final static Logger logger = LoggerFactory.getLogger(ConvertTo.class);

    private HashMap<String, Method> methodMap;

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


    private void getAllMethod() {
        methodMap = new HashMap<>();
        Method[] methods = null;
        methods = productionClazz.getDeclaredMethods();
        for (Method m : methods) {
            if (!methodMap.containsKey(m.getName())) {
                methodMap.put(m.getName(), m);
            }
        }
        Class superClass = productionClazz.getSuperclass();
        while (superClass != Object.class) {
            methods = superClass.getDeclaredMethods();
            for (Method m : methods) {
                if (!methodMap.containsKey(m.getName())) {
                    methodMap.put(m.getName(), m);
                }
            }
            superClass = superClass.getSuperclass();
        }
    }

    @SuppressWarnings("unchecked")
    public <T,R> R convertTo(T source,Class<R> clazz) {
        Object produceObj = null;
        this.source = source;
        sourceClazz = source.getClass();
        productionClazz = clazz;
        try {
            produceObj = clazz.newInstance();
            getAllMethod();
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
//                setter = getSetter(productionClazz.getDeclaredField(setField.value()), productionClazz);
                setter = getSetter(getFieldAnywhere(productionClazz, setField.value()), productionClazz);
                setter.invoke(
                        produceObj,
                        getter.invoke(source)
                );
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return (T) produceObj;
    }

    public Field getFieldAnywhere(Class clazz, String fieldName) throws NoSuchFieldException {
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

    public Field[] getAnotatedField() {
        Field[] fields = getAllField();
        Stream<Field> stream = Stream.of(fields);
        return stream.filter((Field f) -> f.isAnnotationPresent(ReflectField.class)).toArray(Field[]::new);
    }

    public Field[] getAllField() {
        HashSet<Field> fieldSet = new HashSet<>();
        fieldSet.addAll(Arrays.asList(sourceClazz.getDeclaredFields()));
        Class superClazz = sourceClazz.getSuperclass();
        while (superClazz != Object.class) {
            fieldSet.addAll(Arrays.asList(superClazz.getDeclaredFields()));
            superClazz = superClazz.getSuperclass();
        }
        Field[] fields = new Field[fieldSet.size()];
        return fieldSet.toArray(fields);
    }

}
