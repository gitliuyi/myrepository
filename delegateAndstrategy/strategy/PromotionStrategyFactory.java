package strategy;

import java.util.HashMap;
import java.util.Map;

/*
create by Jack on 2019/3/20
 */
public class PromotionStrategyFactory {

    private PromotionStrategyFactory(){}

    public static Map<StrategyEnum,PromotionStrategy> map=new HashMap<StrategyEnum,PromotionStrategy>();

    static{
        map.put(StrategyEnum.CASHBACK,new CashbackStrategy());
        map.put(StrategyEnum.DISCOUNT,new DiscountStrategy());
    }

    public static PromotionStrategy getPromotionStrategy(StrategyEnum strategy){
        return map.get(strategy);
    }

//    interface inner{
//        String cashback="返现";
//        String discount="打折";
//    }

}
