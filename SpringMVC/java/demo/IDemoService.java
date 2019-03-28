package demo;

import core.ModelAndView;

/*
create by Jack on 2019/3/26
 */
public interface IDemoService {

    public String query(String name);

    public String edit(String name,String pwd);
}
