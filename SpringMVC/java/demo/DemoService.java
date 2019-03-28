package demo;

import Annotation.Service;
import core.ModelAndView;

/*
create by Jack on 2019/3/26
 */
@Service
public class DemoService implements IDemoService {
    @Override
    public String query(String name) {
        return name+"先生";
    }

    @Override
    public String edit(String name, String pwd) {
        return "尊敬的"+name+"先生，您的密码是"+pwd;
    }
}