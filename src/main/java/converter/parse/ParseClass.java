package converter.parse;

import com.jd.virtual.gateway.converter.annotation.Source;
import com.jd.virtual.gateway.converter.classTree.ConvertMutableTreeNode;

public class ParseClass {
    Class<?> clazz ;

    ConvertMutableTreeNode root;

    public boolean parse(){
        Source sourceMsg =clazz.getAnnotation(Source.class);
        if(sourceMsg ==null){
            throw new IllegalStateException("No point out Source");
        }else if (true) {
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
