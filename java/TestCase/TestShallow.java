package TestCase;

import shallow.Jinggubang;
import shallow.Sunwukong;

import java.util.ArrayList;

/*
create by Jack on 2019/3/16
 */
public class TestShallow {

    public static void main(String[] args) throws Exception{
        Sunwukong kong=new Sunwukong(1,new ArrayList(),new Jinggubang());
        Sunwukong copy=kong.cloneown();
        System.out.println(kong==copy);
        System.out.println(kong.getId()==copy.getId());
        System.out.println(kong.getList()==copy.getList());
        System.out.println(kong.getBang()==copy.getBang());
        System.out.println("==================");
        Sunwukong copyy=kong.clone();
        System.out.println(kong==copyy);
        System.out.println(kong.getId()==copyy.getId());
        System.out.println(kong.getList()==copyy.getList());
        System.out.println(kong.getBang()==copyy.getBang());
    }
}
