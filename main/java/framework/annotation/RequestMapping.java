package framework.annotation;

import java.lang.annotation.*;

/*
create by Jack on 2019/4/20
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {

    String value() default "";
}
