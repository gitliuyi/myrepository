package framework.aop.aspect;

import framework.aop.intercept.MethodInterceptor;
import framework.aop.intercept.MethodInvocation;

import java.lang.reflect.Method;

/*
create by Jack on 2019/4/21
 */
public class MethodBeforeAdviceInteceptor extends AbstractAspectAdvice implements MethodInterceptor {

    private JoinPoint joinPoint;

    public MethodBeforeAdviceInteceptor(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }

    public void before() throws Throwable{
        //method.invoke(target);
        super.invokeAdviceMethod(this.joinPoint,null,null);
    }
    @Override
    public Object invoke(MethodInvocation mi) throws Throwable{
        this.joinPoint=mi;
        before();
        return mi.proceed();
    }
}