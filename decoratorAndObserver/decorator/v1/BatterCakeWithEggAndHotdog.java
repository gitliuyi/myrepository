package decorator.v1;

/*
create by Jack on 2019/3/21
 */
public class BatterCakeWithEggAndHotdog extends BatterCakeWithEgg {

    public String getMsg(){
        return super.getMsg()+"+1个热狗";
    }

    public int getPrice(){
        return super.getPrice()+2;
    }
}
