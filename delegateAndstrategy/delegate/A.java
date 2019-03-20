package delegate;

/*
create by Jack on 2019/3/19
 */
public class A implements IEmployee {
    @Override
    public void work() {
        System.out.println("我是A员工，我在喝酒");
    }
}
