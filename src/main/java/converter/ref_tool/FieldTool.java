package converter.ref_tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


public class FieldTool {

    public static Logger logger = LoggerFactory.getLogger(FieldTool.class);

    public Field[] getAllField(Class clazz) {
        HashSet<Field> fieldSet = new HashSet<>();
        while (clazz != Object.class) {
            fieldSet.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldSet.size()];
        return fieldSet.toArray(fields);
    }

    public Map<String, Object> getAllFieldMap(Class<?> clazz) {
        Field[] fields = getAllField(clazz);
        HashMap<String, Object> fieldMap = new HashMap<>();
        for (Field f : fields) {
            fieldMap.put(f.getName(), null);
        }
        return fieldMap;
    }

    public Map<String, Object> getAllFieldMap(Object POJO) {
        Class<?> clazz = POJO.getClass();
        Map<String, Object> fieldMap = getAllFieldMap(clazz);
        for (Map.Entry<String, Object> entry : fieldMap.entrySet()) {
            Object fieldObject = null;
            try {
                fieldObject = clazz.getMethod("get" + toUpperFirstCase(entry.getKey()));
            } catch (NoSuchMethodException e) {
                logger.error("{}'s filed :{} have no reflect getter Method ", clazz.getName(), entry.getKey());
            }
            entry.setValue(fieldObject);
        }
        return fieldMap;
    }

    public String toUpperFirstCase(String fieldName) {
        return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

}
