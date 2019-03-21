package guavaByown;

import com.google.common.eventbus.Subscribe;
import guava.GuavaEvent;
import observer.Question;

import java.util.Observable;
import java.util.Observer;

/*
create by Jack on 2019/3/21
 */
public class Teacher extends GuavaEvent {

    private String name;

    public Teacher(String name){
        this.name=name;
    }

    @Subscribe
        public void subscribe(Question question){
        System.out.println("---------------------");
        System.out.println(name+"老师\n"+"您收到了来自GPer生态圈的提问，问题是:"+question.getContent()+"\n"+"提问者："+question.getUsername());
    }
}
