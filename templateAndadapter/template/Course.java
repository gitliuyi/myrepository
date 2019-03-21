package template;

/*
create by Jack on 2019/3/20
 */
public abstract class Course {

   protected abstract boolean need();

    protected abstract void check();

   public final void create(){
       prepare();
       live();
       post();
   }

    protected final void prepare(){
        System.out.println("预习");
    }

    protected final void live(){
        System.out.println("直播");
    }

    protected final void post(){
       if(need()){
           check();
       }
    }


}
