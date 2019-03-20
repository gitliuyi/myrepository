package strategy;

/*
create by Jack on 2019/3/20
 */
public class DiscountStrategy implements PromotionStrategy {
    @Override
    public void promotion() {
        System.out.println("八折促销");
    }
}
