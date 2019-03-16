package hungry;

/*
create by Jack on 2019/3/14
 */
public class HungrySingleton {

    private static final HungrySingleton hungrySingleton;

    static{
        hungrySingleton=new HungrySingleton();
    }

    private HungrySingleton (){

    }

    public static HungrySingleton getInstance(){
        return  hungrySingleton;
    }
}
