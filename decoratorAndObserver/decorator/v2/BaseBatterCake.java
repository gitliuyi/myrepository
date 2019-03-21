package decorator.v2;

/*
create by Jack on 2019/3/21
 */
public class BaseBatterCake extends BatterCake {
    @Override
    public String getMsg() {
        return "煎饼";
    }

    @Override
    public int getPrice() {
        return 5;
    }
}
