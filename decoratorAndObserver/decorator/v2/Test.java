package decorator.v2;

/*
create by Jack on 2019/3/21
 */
public class Test {

    public static void main(String[] args) {
        BatterCake cake=new BaseBatterCake();
        System.out.println(cake.getMsg()+",价格为"+cake.getPrice());
//        cake=new EggDecorator(cake);
//        System.out.println(cake.getMsg()+",价格为"+cake.getPrice());
//        cake=new EggDecorator(cake);
//        System.out.println(cake.getMsg()+",价格为"+cake.getPrice());
//        cake=new HotdogDecorator(cake);
//        System.out.println(cake.getMsg()+",价格为"+cake.getPrice());

        //加50个鸡蛋和10个热狗
        for(int i=1;i<=50;i++){
            cake=new EggDecorator(cake);
        }
        for (int i=1;i<=10;i++) {
            cake=new HotdogDecorator(cake);
        }
        System.out.println(cake.getMsg()+",价格为"+cake.getPrice());

    }
}
