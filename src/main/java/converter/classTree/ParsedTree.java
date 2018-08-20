package converter.classTree;


import java.lang.reflect.Field;
import java.util.HashSet;

public class ParsedTree {

    private BinaryTree<Pair> root;

    private static final HashSet<Class> PRIMITIVE_CLASS = new HashSet<>();

    static {
        PRIMITIVE_CLASS.add(Integer.TYPE);
        PRIMITIVE_CLASS.add(Double.TYPE);
        PRIMITIVE_CLASS.add(Float.TYPE);
        PRIMITIVE_CLASS.add(Short.TYPE);
        PRIMITIVE_CLASS.add(Boolean.TYPE);
        PRIMITIVE_CLASS.add(Byte.TYPE);
        PRIMITIVE_CLASS.add(Character.TYPE);
        PRIMITIVE_CLASS.add(String.class);

    }

    enum PRIMITIVE{
    }

    public ParsedTree(String className) {
        root = new BinaryTree<>();
    }

    public ParsedTree(Object object) {
        root = new BinaryTree<>();
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            Pair pair = new Pair(f.getName(), f.getType());
            if (isBasalType(f.getType())) {
                try {
                    pair.setValue(f.get(object));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            root.add(pair);
        }
    }

    public boolean isBasalType(Class clazz) {
        if(PRIMITIVE_CLASS.contains(clazz)){
            return true;
        }
        return false;
    }
}
