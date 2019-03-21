package observerByown;

import java.util.ArrayList;
import java.util.List;

/*
create by Jack on 2019/3/21
 */
public class Observable {
    private String name="GPer生态圈";
    private boolean changed=false;
    private List<Observer> list=new ArrayList<Observer>();
    public void setChanged(){
        changed=true;
    }
    public void addObserver(Observer observer){
        list.add(observer);
    }
    public void notifyObservers(Object object){
        if(changed){
            for(Observer observer:list){
                observer.update(this,object);
            }
        }
    }
    public String getName() {
        return name;
    }
}
