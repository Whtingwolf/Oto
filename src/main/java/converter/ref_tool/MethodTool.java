package converter.ref_tool;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MethodTool {

    public Map<String, Method> getAllMethods(Class clazz){
            HashMap <String,Method> methodMap = new HashMap<>();
            Method[] methods = null;
            while (clazz != Object.class) {
                methods = clazz.getDeclaredMethods();
                for (Method m : methods) {
                    if (!methodMap.containsKey(m.getName())) {
                        methodMap.put(m.getName(), m);
                    }
                }
                clazz = clazz.getSuperclass();
            }
            return methodMap;
    }

    public Method getMethod(Class clazz,String methodName){
        Map<String,Method> methodMap = getAllMethods(clazz);
        return methodMap.get(methodName);
    }

    public String toUpperFirstCase(String fieldName) {
        return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }
}
