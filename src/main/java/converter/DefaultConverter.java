package converter;

import java.lang.reflect.Method;
import java.util.HashMap;

public class DefaultConverter {

    private Object source;

    private Class<?> sourceClazz;

    private Class<?> production;

    private HashMap<String, Method> methodMap;

    public DefaultConverter(Object source, Class<?> production) {
        this.source = source;
        this.sourceClazz = source.getClass();
        this.production = production;
    }

    private void getAllMethod() {
        methodMap = new HashMap<>();
        Method[] methods = null;
//        Method [] methods = production.getMethods();
//        for(Method m : methods){
//            methodMap.put(m.getName(),m);
//        }
        methods = production.getDeclaredMethods();
        for (Method m : methods) {
            if (!methodMap.containsKey(m.getName())) {
                methodMap.put(m.getName(), m);
            }
        }
        Class superClass = production.getSuperclass();
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
    public <T> T convertTo() {
        Object produceObj = null;
        try {
            produceObj = production.newInstance();
            getAllMethod();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return (T) produceObj;
    }

}
