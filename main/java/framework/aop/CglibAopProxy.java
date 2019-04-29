package framework.aop;

import framework.aop.support.AdvisedSupport;

/*
create by Jack on 2019/4/21
 */
public class CglibAopProxy implements AopProxy {

    public CglibAopProxy(AdvisedSupport config) {

    }

    @Override
    public Object getProxy() {
        return null;
    }

}