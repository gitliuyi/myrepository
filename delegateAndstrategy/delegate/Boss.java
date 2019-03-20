package delegate;

/*
create by Jack on 2019/3/19
 */
public class Boss {

    private Manager manager;



    public Boss(Manager manager){
        this.manager=manager;
    }

    public static void main(String[] args) {
        Boss boss=new Boss(new Manager());
        boss.manager.work("泡妞");

    }
}
