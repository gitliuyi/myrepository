package dynamicproxy;

import staticproxy.Person;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*
create by Jack on 2019/3/17
 */
public class JDKMeipo implements InvocationHandler{

    private Person person;

    public JDKMeipo(Person person){
        this.person=person;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object o=method.invoke(person,args);
        after();
        return o;
    }

    public Person getInstance(){
        Class<?> clazz=person.getClass();
        return (Person)Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);

    }

    public void before(){
        System.out.println("媒婆开始物色对象啦");
    }

    public void after(){
        System.out.println("媒体物色对象结束");
    }

}
