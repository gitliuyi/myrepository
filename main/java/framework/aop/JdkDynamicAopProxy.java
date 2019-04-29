package framework.aop;

import framework.aop.intercept.MethodInvocation;
import framework.aop.support.AdvisedSupport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Map;

/*
create by Jack on 2019/4/21
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {

    private AdvisedSupport advised;

    public JdkDynamicAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        List<Object> advices=advised.getAdvices(method,advised.getTargetClass());
        MethodInvocation mi=new MethodInvocation(proxy,advised.getTarget(),method,args,advised.getTargetClass(),advices);
        return mi.proceed();
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(advised.getTargetClass().getClassLoader(), advised.getTargetClass().getInterfaces(), this);
    }
}