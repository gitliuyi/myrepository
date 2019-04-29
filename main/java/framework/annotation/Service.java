package framework.annotation;

import java.lang.annotation.*;

/*
create by Jack on 2019/4/19
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Service {

    String value() default "";
}
