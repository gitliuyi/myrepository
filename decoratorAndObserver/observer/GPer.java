package observer;

import java.util.Observable;

/*
create by Jack on 2019/3/21
 */
public class GPer extends Observable {

    private static GPer gper=null;
    private String name="GPer生态圈";
    private GPer(){};
    public static GPer getInstance(){
        if(gper==null){
            gper=new GPer();
        }
        return gper;
    }
    public String getName() {
        return name;
    }
    public void publishQuestion(Question question){
        System.out.println(question.getUsername()+"在"+this.name+"上提交了一个问题");
        setChanged();
        notifyObservers(question);
    }
}
