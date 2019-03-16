package serial;

import java.io.Serializable;

/*
create by Jack on 2019/3/15
 */
public class SerialSingleton implements Serializable {

    private SerialSingleton(){}

    private final static SerialSingleton INSTANCE=new SerialSingleton();

    public static SerialSingleton getInstance(){
        return INSTANCE;
    }

//    private Object readResolve(){
//        return INSTANCE;
//    }

}
