package testcase;

import dynamic.Girl;
import dynamic.Manager;
import staticproxy.Person;

/*
create by Jack on 2019/3/18
 */
public class TestDynamic {

    public static void main(String[] args) {
        Manager m=new Manager(new Girl());
        Person p=(Person) m.getInstance();
        p.findJob();

    }
}
