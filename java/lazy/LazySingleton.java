package lazy;

/*
create by Jack on 2019/3/14
 */
public class LazySingleton {

    private static LazySingleton lazy=null;

    LazySingleton(){

    }

    public static synchronized LazySingleton getInstance(){
        if(null==lazy){
            lazy=new LazySingleton();
        }
        return lazy;
    }
}
