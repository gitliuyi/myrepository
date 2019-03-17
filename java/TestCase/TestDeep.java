package TestCase;

import shallow.Jinggubang;
import deep.Sunwukong;

import java.util.ArrayList;

/*
create by Jack on 2019/3/16
 */
public class TestDeep {

    public static void main(String[] args) {
        Sunwukong kong=new Sunwukong(1,new ArrayList(),new Jinggubang());
        Sunwukong copy=kong.clone();
        System.out.println(kong==copy);
        System.out.println(kong.getId()==copy.getId());
        System.out.println(kong.getList()==copy.getList());
        System.out.println(kong.getBang()==copy.getBang());
    }
}
