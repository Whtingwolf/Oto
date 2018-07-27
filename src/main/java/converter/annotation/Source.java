package converter.annotation;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Repeatable(value = Sources.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface Source {

    String outcome();

    String converter();

}
