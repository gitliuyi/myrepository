package SimpleFactory;

import CourseUtil.ICourse;

public class CourseFactory {

//    public ICourse create(String name){
//        if("Java".equals(name)){
//            return new JavaCourse();
//        }else{
//            return null;
//        }
//    }
    public ICourse create(Class clazz) {
        if (clazz != null) {
            try {
                return (ICourse) clazz.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
