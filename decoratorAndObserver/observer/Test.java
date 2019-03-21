package observer;

/*
create by Jack on 2019/3/21
 */
public class Test {

    public static void main(String[] args) {
        GPer gper =GPer.getInstance();
        Teacher tom=new Teacher("Tom");
        Question question=new Question("小明","观察者适用于哪些场景？");
        gper.addObserver(tom);
        gper.publishQuestion(question);

    }
}
