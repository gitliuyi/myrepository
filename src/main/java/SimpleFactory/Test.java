package SimpleFactory;

import CourseUtil.ICourse;
import CourseUtil.PythonCourse;

public class Test {

    public static void main(String[] args) {

       CourseFactory factory=new CourseFactory();
       ICourse course= factory.create(PythonCourse.class);
       course.record();


    }
}
