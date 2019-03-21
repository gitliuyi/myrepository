package template;

/*
create by Jack on 2019/3/20
 */
public class JavaCourse extends Course {
    @Override
    protected boolean need() {
        return false;
    }

    @Override
    protected void check() {
        System.out.println("检查Java作业");
    }
}
