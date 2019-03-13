package AbstractFactory;

public class Test {

    public static void main(String[] args) {
        ICourseFactory factory=new PythonCourseFactory();
        factory.createVideo().play();



    }
}
