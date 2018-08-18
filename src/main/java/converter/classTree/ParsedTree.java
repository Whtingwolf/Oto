package converter.classTree;


import java.lang.reflect.Field;

public class ParsedTree {

    private BinaryTree<Pair> root;

    public ParsedTree(String className) {
        root = new BinaryTree<>();

    }

    public ParsedTree(Object object) {
        root = new BinaryTree<>();
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            Pair pair = new Pair(f.getName(), f.getType());
            if (isBasalType(f.getDeclaringClass())) {
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
        return true;
    }
}
