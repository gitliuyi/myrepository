package framework.aop.aspect;

import framework.aop.intercept.MethodInterceptor;
import framework.aop.intercept.MethodInvocation;

import java.lang.reflect.Method;

/*
create by Jack on 2019/4/21
 */
public class MethodAfterAdviceInteceptor extends AbstractAspectAdvice implements MethodInterceptor {

    private JoinPoint joinPoint;

    public MethodAfterAdviceInteceptor(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }

    public void after() throws Throwable{
        super.invokeAdviceMethod(this.joinPoint,null,null);
    }
    @Override
    public Object invoke(MethodInvocation mi) throws Throwable{
        this.joinPoint=mi;
        Object retValue=mi.proceed();
        after();
        return retValue;
    }
}