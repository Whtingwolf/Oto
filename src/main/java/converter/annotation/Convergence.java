package converter.annotation;


import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Repeatable(value = Convergences.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface Convergence {

    String source();

    String converter();

}
