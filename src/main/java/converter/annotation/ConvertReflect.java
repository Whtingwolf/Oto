package converter.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(value = Reflects.class)
public @interface ConvertReflect {

    String reflectName() default "";

    String converter() default "defaultConverter";

}
