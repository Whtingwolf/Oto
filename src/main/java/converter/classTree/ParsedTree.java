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
        root = parseNode("root", object);
//        root = new BinaryTree<>();
//        root.setData(new Pair(object.getClass()));
//        Class clazz = object.getClass();
//        Field[] fields = clazz.getDeclaredFields();
//        for (Field f : fields) {
//            Pair pair = new Pair(f.getName(), f.getType());
//            if (isBasalType(f.getType())) {
//                try {
//                    pair.setValue(f.get(object));
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//            }
//            root.add(pair);
//        }
    }

    public BinaryTree<Pair> parseNode(String name, Object object){
        BinaryTree<Pair> node = new BinaryTree<>();
        node.setData(new Pair(name,object.getClass()));
        Field [] fields = object.getClass().getDeclaredFields();
        for(Field f:fields){
            f.setAccessible(true);
            if (isBasalType(f.getType())) {
                Pair pair = new Pair(f.getName(), f.getType());
                try {
                    pair.setValue(f.get(object));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                node.add(pair);
            }else {
                try {
                    node.add(parseNode(f.getName(), f.get(object)));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return node;
    }

    public boolean isBasalType(Class clazz) {
        if(PRIMITIVE_CLASS.contains(clazz)){
            return true;
        }
        return false;
    }
}
