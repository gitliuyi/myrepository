package AbstractFactory;

import CourseUtil.ICourse;
import CourseUtil.JavaCourse;
import CourseUtil.PythonCourse;

public class PythonCourseFactory implements  ICourseFactory{
    @Override
    public ICourse createCourse() {
        return new PythonCourse();
    }

    @Override
    public IVedio createVideo() {
        return new PythonVedio();
    }

    @Override
    public INote createNote() {
        return new PythonNote();
    }
}
