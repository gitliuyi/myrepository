package framework.aop.aspect;

import java.lang.reflect.Method;

/*
create by Jack on 2019/4/22
 */
public interface JoinPoint {
    Object getThis();
    Object[] getArguments();
    Method getMethod();

    void setUserAttribute(String key, Object value);

    Object getUserAttribute(String key);
}