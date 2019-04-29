package demo;

import framework.annotation.Service;

import java.util.Objects;

/*
create by Jack on 2019/4/19
 */
@Service
public class DemoService implements IDemoService {

    @Override
    public String query(String name) {
        System.out.println("方法被执行");
        return "正在查询"+name;
    }

    @Override
    public String edit(String name, String pwd) {
        return name+"正在修改"+pwd+"密码";
    }



}