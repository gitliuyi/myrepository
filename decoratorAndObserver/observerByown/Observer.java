package observerByown;

import observer.GPer;
import observer.Question;

/*
create by Jack on 2019/3/21
 */
public class Observer {

    private String name;

    public Observer(String name){
        this.name=name;
    }

    public void update(Observable o,Object arg){
        Question question= (Question) arg;
        System.out.println("---------------------");
        System.out.println(name+"老师\n"+"您收到了来自"+o.getName()+"的提问，问题是:"+question.getContent()+"\n"+"提问者："+question.getUsername());
    }
}
