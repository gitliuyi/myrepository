package framework.aop.intercept;

/*
create by Jack on 2019/4/21
 */
public interface MethodInterceptor {
    Object invoke(MethodInvocation invocation)throws Throwable;
}