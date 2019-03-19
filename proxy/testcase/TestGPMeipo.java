package testcase;

import dynamicproxy.Boy;
import dynamicproxy.GPMeipo;
import dynamicproxy.JDKMeipo;
import staticproxy.Person;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

/*
create by Jack on 2019/3/17
 */
public class TestGPMeipo {

    public static void main(String[] args) throws Exception {
        Person person = new GPMeipo(new Boy()).getInstance();
        person.findLove();


    }
}
