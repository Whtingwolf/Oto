package converter.ref_tool;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

public class AnnotationTool {

    public <T extends AnnotatedElement> boolean isAnnotated(T element, Class<? extends Annotation> clazz){
        return element.isAnnotationPresent(clazz);
    }



}
