package delegate;

import java.util.HashMap;
import java.util.Map;

/*
create by Jack on 2019/3/19
 */
public class Manager {

    private Map<String,IEmployee> map=new HashMap<String,IEmployee>();

    public Manager(){
        map.put("喝酒",new A());
        map.put("泡妞",new B());
    }

    public void work(String command){
        map.get(command).work();
    }
}
