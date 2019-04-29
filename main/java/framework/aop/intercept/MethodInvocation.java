package framework.aop.intercept;

import framework.aop.aspect.JoinPoint;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
create by Jack on 2019/4/21
 */
public class MethodInvocation implements JoinPoint {
    private Object proxy;
    private Object target;
    private Method method;
    private Object[] arguments;
    private Class<?> targetClass;
    private List<Object> interceptors;
    private Map<String, Object> userAttributes;
    private int currentInterceptorIndex=-1;

    public MethodInvocation(Object proxy, Object target, Method method, Object[] arguments, Class<?> targetClass, List<Object> interceptors) {
        this.proxy = proxy;
        this.target = target;
        this.method = method;
        this.arguments = arguments;
        this.targetClass = targetClass;
        this.interceptors = interceptors;
    }

    public Object proceed() throws Throwable{
        if(currentInterceptorIndex==interceptors.size()-1){
            return method.invoke(target,arguments);
        }
        Object interceptor=interceptors.get(++currentInterceptorIndex);
        if(interceptor instanceof MethodInterceptor){
            MethodInterceptor mi= (MethodInterceptor) interceptor;
            return mi.invoke(this);
        }
        return null;
    }

    @Override
    public Object getThis() {
        return this.target;
    }

    @Override
    public Object[] getArguments() {
        return this.arguments;
    }

    @Override
    public Method getMethod() {
        return this.method;
    }

    public void setUserAttribute(String key, Object value) {
        if (value != null) {
            if (this.userAttributes == null) {
                this.userAttributes = new HashMap<String,Object>();
            }
            this.userAttributes.put(key, value);
        }
        else {
            if (this.userAttributes != null) {
                this.userAttributes.remove(key);
            }
        }
    }

    public Object getUserAttribute(String key) {
        return (this.userAttributes != null ? this.userAttributes.get(key) : null);
    }
}