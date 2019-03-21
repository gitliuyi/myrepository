package observerByown;

import observer.GPer;
import observer.Question;
import observer.Teacher;

/*
create by Jack on 2019/3/21
 */
public class Test {

    public static void main(String[] args) {
        Observable observable=new Observable();
        Observer   observer=new Observer("Jack");
        Observer   observer2=new Observer("Tom");
        Question question=new Question("小明","观察者适用于哪些场景？");
        observable.addObserver(observer);
        observable.addObserver(observer2);
        System.out.println(question.getUsername()+"在"+observable.getName()+"上提交了一个问题");
        observable.setChanged();
        observable.notifyObservers(question);

    }
}
