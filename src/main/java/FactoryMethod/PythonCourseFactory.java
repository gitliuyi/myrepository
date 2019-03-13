package FactoryMethod;

import CourseUtil.ICourse;
import CourseUtil.JavaCourse;
import CourseUtil.PythonCourse;

public class PythonCourseFactory implements ICourseFactory {
    @Override
    public ICourse create() {
        return new PythonCourse();
    }
}
