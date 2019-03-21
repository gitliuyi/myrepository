package decorator.v1;

/*
create by Jack on 2019/3/21
 */
public class Test {

    public static void main(String[] args) {
        BatterCake cake=new BatterCake();
        System.out.println(cake.getMsg()+",价格为"+cake.getPrice());

        BatterCakeWithEgg cake1=new BatterCakeWithEgg();
        System.out.println(cake1.getMsg()+",价格为"+cake1.getPrice());

        BatterCakeWithEggAndHotdog cake2=new BatterCakeWithEggAndHotdog();
        System.out.println(cake2.getMsg()+",价格为"+cake2.getPrice());
    }
}
