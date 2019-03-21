package guava;

import com.google.common.eventbus.Subscribe;

/*
create by Jack on 2019/3/21
 */
public class GuavaEvent {
    @Subscribe
    public void subscribe(String str){
        System.out.println("执行subscribe方法，传入的参数是"+str);
    }
}
