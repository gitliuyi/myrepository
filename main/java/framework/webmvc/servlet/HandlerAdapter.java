package framework.webmvc.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
create by Jack on 2019/4/20
 */
/*
paramIndex{HttpServletRequest=0,HttpServletResponse=1,name=2,pwd=3}
 */
public class HandlerAdapter {
    private Map<String,Integer> paramIndex=new HashMap<String,Integer>();

    public HandlerAdapter(Map<String, Integer> paramIndex) {
        this.paramIndex = paramIndex;
    }
//        /web/edit?name=Tom&name=Jack&pwd=123
    public ModelAndView hand(HttpServletRequest req, HttpServletResponse resp, HandlerMapping handler) {
        Object[] param=new Object[paramIndex.size()];
        Map<String, String[]> map=req.getParameterMap();//{name=[Tom,Jack],pwd=123
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            String key=entry.getKey();
            if(paramIndex.containsKey(key)){
                param[paramIndex.get(key)]= Arrays.toString(entry.getValue()).replaceAll("\\[|\\]","");
            }
        }
        param[paramIndex.get("HttpServletRequest")]=req;
        param[paramIndex.get("HttpServletResponse")]=resp;
        ModelAndView mv=null;
        try {
            mv= (ModelAndView) handler.getMethod().invoke(handler.getController(),param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }
}