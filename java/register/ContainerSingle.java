package register;

import java.util.HashMap;
import java.util.Map;

/*
create by Jack on 2019/3/16
 */
public class ContainerSingle {

    private ContainerSingle(){}

    private static Map<String,ContainerSingle> ioc=new HashMap<String,ContainerSingle>();

    public static synchronized ContainerSingle getInstance(String classname){
        ContainerSingle obj=(ContainerSingle)ioc.get(classname);
        if(obj==null){
            obj=new ContainerSingle();
            ioc.put(classname,obj);
            return obj;
        }
        return obj;
    }

}
