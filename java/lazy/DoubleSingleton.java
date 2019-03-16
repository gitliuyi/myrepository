package lazy;

/*
create by Jack on 2019/3/14
 */
public class DoubleSingleton {

    private static DoubleSingleton lazy=null;

    DoubleSingleton(){

    }

    public static  DoubleSingleton getInstance(){
        if(null==lazy){
            synchronized (DoubleSingleton.class){
                if(null==lazy) {
                    lazy = new DoubleSingleton();
                }
            }
        }
        return lazy;
    }
}
