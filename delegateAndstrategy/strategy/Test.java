package strategy;

/*
create by Jack on 2019/3/20
 */
public class Test {

//    public static void main(String[] args) {
//        PromotionActivity activity=new PromotionActivity(new DiscountStrategy());
//        activity.execute();
//    }

//    public static void main(String[] args) {
//        String s="Discount";
//        PromotionActivity activity=null;
//        if("Cashback".equals(s)){
//            activity=new PromotionActivity(new CashbackStrategy());
//        }else if("Discount".equals(s)){
//            activity=new PromotionActivity(new DiscountStrategy());
//        }
//        activity.execute();
//    }

    public static void main(String[] args) {
        PromotionActivity activity=new PromotionActivity( PromotionStrategyFactory.getPromotionStrategy(StrategyEnum.CASHBACK));
        activity.execute();
    }
}
