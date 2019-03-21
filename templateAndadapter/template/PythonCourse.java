package template;

/*
create by Jack on 2019/3/20
 */
public class PythonCourse extends Course {
    @Override
    protected boolean need() {
        return true;
    }

    @Override
    protected void check() {
        System.out.println("检查Python作业");
    }
}
