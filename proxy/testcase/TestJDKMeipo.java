package testcase;

import dynamicproxy.Boy;
import dynamicproxy.JDKMeipo;
import staticproxy.Person;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

/*
create by Jack on 2019/3/17
 */
public class TestJDKMeipo {

    public static void main(String[] args) throws Exception {
        Person person = new JDKMeipo(new Boy()).getInstance();
        person.findLove();

        byte[] bytes= ProxyGenerator.generateProxyClass("proxy0",new Class[]{Person.class});
        FileOutputStream fos = new FileOutputStream("D:\\Program Files\\IEDA\\projects\\proxy0.class");
        fos.write(bytes);
        fos.close();

    }
}
