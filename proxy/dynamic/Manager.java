package dynamic;

import staticproxy.Person;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*
create by Jack on 2019/3/18
 */
public class Manager implements InvocationHandler {

    private Person p;

    public Manager(Person p){
        this.p=p;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
         before();
         Object o=method.invoke(p,args);
         after();
         return o;
    }

    private void before() {
        System.out.println("开始");
    }

    private void after() {
        System.out.println("结尾");
    }

    public Object getInstance(){
        return Proxy.newProxyInstance(p.getClass().getClassLoader(),p.getClass().getInterfaces(),this);
    }
}
