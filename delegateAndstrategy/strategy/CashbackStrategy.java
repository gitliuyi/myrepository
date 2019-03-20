package strategy;

/*
create by Jack on 2019/3/20
 */
public class CashbackStrategy implements PromotionStrategy {
    @Override
    public void promotion() {
        System.out.println("返现促销");
    }
}
