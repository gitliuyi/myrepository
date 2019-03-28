package core;

import Annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
create by Jack on 2019/3/27
 */
public class HandlerAdapter {
// { HttpServletRequest=0, HttpServletResponse=1, name=2, pwd=3 }
    private Map<String,Integer> paramMap;

    public HandlerAdapter(Map<String,Integer> paramMap){
        this.paramMap=paramMap;
    }
// [Tom,Jack]
    public ModelAndView hand(HttpServletRequest req, HttpServletResponse resp, HandlerMapping handlerMapping) {
        Map<String, String[]> map=req.getParameterMap();//{ name=Tom, pwd=#%^% }
        Class<?>[] clazzs= handlerMapping.getMethod().getParameterTypes();
        Object[] params=new Object[clazzs.length];
        for (Map.Entry<String,String[]> entry:map.entrySet() ) {
            String key=entry.getKey();
            if(paramMap.containsKey(key)){
                params[paramMap.get(key)]=Arrays.toString(entry.getValue()).replaceAll("\\[|\\]","");
            }
        }
        params[paramMap.get(HttpServletRequest.class.getName())]=req;
        params[paramMap.get(HttpServletResponse.class.getName())]=resp;
        try {
            return (ModelAndView)handlerMapping.getMethod().invoke(handlerMapping.getController(),params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}