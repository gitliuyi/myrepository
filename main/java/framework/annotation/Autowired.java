package framework.annotation;

import java.lang.annotation.*;

/*
create by Jack on 2019/4/19
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {

    String value() default "";
}
