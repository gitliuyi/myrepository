package demo;

import framework.annotation.Autowired;
import framework.annotation.Controller;
import framework.annotation.RequestMapping;
import framework.annotation.RequestParam;
import framework.webmvc.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/*
create by Jack on 2019/4/19
 */
@Controller
@RequestMapping("/web")
public class MyAction {

    @Autowired
    private IDemoService demoService;

    @RequestMapping("/query")
    public ModelAndView query(HttpServletRequest req, HttpServletResponse res, @RequestParam("name") String name){
        String result=demoService.query(name);
        Map<String,Object> model=new HashMap();
        model.put("result",result);
        return  new ModelAndView("query.html",model);
    }

    @RequestMapping("/edit")
    public ModelAndView edit(HttpServletRequest req, HttpServletResponse res, @RequestParam("name") String name, @RequestParam("pwd") String pwd) {
        String result=demoService.edit(name, pwd);
        Map model=new HashMap();
        model.put("result",result);
        return  new ModelAndView("edit.html",model);
    }

    @RequestMapping("/error")
    public ModelAndView error(HttpServletRequest req, HttpServletResponse res,@RequestParam("name") String name) {
        Map model=new HashMap();
        model.put("detail","服务器崩溃了");
        model.put("name",name);
        return  new ModelAndView("500.html",model);
    }
}