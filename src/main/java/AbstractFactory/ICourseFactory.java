package AbstractFactory;

import CourseUtil.ICourse;

public interface ICourseFactory {

    public ICourse createCourse();

    public INote createNote();

    public IVedio createVideo();
}
