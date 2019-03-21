package guava;

import com.google.common.eventbus.EventBus;

/*
create by Jack on 2019/3/21
 */
public class Test {

    public static void main(String[] args) {
        EventBus bus=new EventBus();
        GuavaEvent event=new GuavaEvent();
        bus.register(event);
        bus.post("Jack");
    }
}
