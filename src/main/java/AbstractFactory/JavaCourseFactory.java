package AbstractFactory;

import CourseUtil.ICourse;
import CourseUtil.JavaCourse;

public class JavaCourseFactory implements  ICourseFactory{
    @Override
    public IVedio createVideo() { return new JavaVedio(); }

    @Override
    public ICourse createCourse() {
        return new JavaCourse();
    }

    @Override
    public INote createNote() {
        return new JavaNote();
    }
}
