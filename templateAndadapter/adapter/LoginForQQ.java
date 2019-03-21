package adapter;

/*
create by Jack on 2019/3/21
 */
public class LoginForQQ implements ILogin ,INewLogin{
    @Override
    public void login(String name, String pwd) {
        System.out.println("用户为"+name+"密码为"+pwd+"登陆啦！");
    }

    @Override
    public void login(String id) {
        this.login("Jack","123");
    }
}
