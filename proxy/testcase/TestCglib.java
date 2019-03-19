package testcase;

import cglib.Monitor;
import cglib.Young;

/*
create by Jack on 2019/3/18
 */
public class TestCglib {

    public static void main(String[] args) {
        Monitor monitor = new Monitor();
        Young y=(Young)monitor.getInstance(new Young().getClass());
        y.findWork();
    }
}
