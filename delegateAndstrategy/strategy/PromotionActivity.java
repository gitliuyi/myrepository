package strategy;

/*
create by Jack on 2019/3/20
 */
public class PromotionActivity {

    private PromotionStrategy strategy;

    public PromotionActivity(PromotionStrategy strategy){
        this.strategy=strategy;
    }

    public void execute(){
        strategy.promotion();
    }
}
