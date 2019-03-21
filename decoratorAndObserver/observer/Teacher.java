package observer;

import java.util.Observable;
import java.util.Observer;

/*
create by Jack on 2019/3/21
 */
public class Teacher implements Observer {

    private String name;

    public Teacher(String name){
        this.name=name;
    }
    @Override
    public void update(Observable o, Object arg) {
        GPer gper= (GPer) o;
        Question question= (Question) arg;
        System.out.println("---------------------");
        System.out.println(name+"老师\n"+"您收到了来自"+gper.getName()+"的提问，问题是:"+question.getContent()+"\n"+"提问者："+question.getUsername());

    }
}
