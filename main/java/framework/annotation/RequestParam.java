package framework.annotation;

import java.lang.annotation.*;

/*
create by Jack on 2019/4/20
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestParam {
    String value() default "";
}