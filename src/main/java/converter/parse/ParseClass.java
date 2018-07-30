package converter.parse;


import converter.annotation.Source;
import converter.classTree.ConvertMutableTreeNode;

public class ParseClass {
    Class<?> clazz;

    ConvertMutableTreeNode root;

    public boolean parse() {
        Source sourceMsg = clazz.getAnnotation(Source.class);
        if (sourceMsg == null) {
            throw new IllegalStateException("No point out Source");
        } else if (true) {
            return true;
        }
        return true;
    }


    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

}
