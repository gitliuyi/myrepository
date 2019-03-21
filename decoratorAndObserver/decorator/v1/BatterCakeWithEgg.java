package decorator.v1;

/*
create by Jack on 2019/3/21
 */
public class BatterCakeWithEgg extends BatterCake {

    public String getMsg(){
        return super.getMsg()+"+1个鸡蛋";
    }

    public int getPrice(){
        return super.getPrice()+1;
    }
}
