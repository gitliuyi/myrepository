package testcase;

import staticproxy.Father;
import staticproxy.Son;

/*
create by Jack on 2019/3/17
 */
public class TestStaticProxy {

    public static void main(String[] args) {
        Father father=new Father(new Son());
        father.findLove();

    }
}
