package decorator.v2;

/*
create by Jack on 2019/3/21
 */
public class HotdogDecorator extends BaseBatterCake {

    private BatterCake cake;

    public HotdogDecorator(BatterCake cake){
        this.cake=cake;
    }

    @Override
    public String getMsg() {
        return cake.getMsg()+"+1个热狗";
    }

    @Override
    public int getPrice() {
        return cake.getPrice()+2;
    }
}
