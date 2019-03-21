package adapter;

/*
create by Jack on 2019/3/21
 */
public class Test {

    public static void main(String[] args) {
        LoginForQQ in=new LoginForQQ();
        in.login("Jack","123");
        System.out.println("------------------");
        in.login("1");
    }
}
