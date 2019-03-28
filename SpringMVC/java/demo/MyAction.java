package demo;

import Annotation.Autowired;
import Annotation.Controller;
import Annotation.RequestMapping;
import Annotation.RequestParam;
import core.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/*
create by Jack on 2019/3/26    /my/query?name=Tom&name=Jack
 */
@Controller
@RequestMapping("/my")
public class MyAction extends HttpServlet {
    @Autowired private IDemoService demoService;
    @RequestMapping("/query.*")
    public ModelAndView queryName(HttpServletRequest req, HttpServletResponse resp, @RequestParam("name") String name){
        String result=demoService.query(name);
        Map<String,Object> m=new HashMap<>();
        m.put("name",result);
        return new ModelAndView("first.html",m);
    }
    @RequestMapping("/edit")
    public ModelAndView editName(HttpServletRequest req,HttpServletResponse resp, @RequestParam("name") String name,@RequestParam("pwd") String pwd){
        String result=demoService.edit(name,pwd);
        Map<String,Object> m=new HashMap<>();
        m.put("name",result);
        return new ModelAndView("second.html",m);
    }
    /*
    @RequestMapping("/add.json")
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response,
								@RequestParam("name") @RequestParam2("name2") String name,@RequestParam("addr") String addr){
		String result = modifyService.add(name,addr);
		System.out.println(result);
		return out(response,result);
	}
     */

}