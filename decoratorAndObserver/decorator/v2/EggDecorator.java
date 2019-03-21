package decorator.v2;

/*
create by Jack on 2019/3/21
 */
public class EggDecorator extends BaseBatterCake {

    private BatterCake cake;

    public  EggDecorator(BatterCake cake){
        this.cake=cake;
    }

    @Override
    public String getMsg() {
        return cake.getMsg()+"+1个鸡蛋";
    }

    @Override
    public int getPrice() {
        return cake.getPrice()+1;
    }
}
