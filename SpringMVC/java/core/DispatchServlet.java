package core;

import Annotation.*;
import demo.MyAction;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
create by Jack on 2019/3/26
 */
public class DispatchServlet extends HttpServlet {

    private List<HandlerMapping> handlerMappings=new ArrayList<>();
    private Map<HandlerMapping,HandlerAdapter> handlerAdapters=new HashMap<>();
    private List<ViewResolver> viewResolvers=new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HandlerMapping handlerMapping=getHandler(req);
        if(handlerMapping==null){
            resp.getWriter().println("<p>404 Not Found</p>");
        }else{
            HandlerAdapter handlerAdapter=getAdapter(handlerMapping);
            ModelAndView mv=handlerAdapter.hand(req,resp,handlerMapping);
            outputResult(resp,mv);
        }

    }

    private void outputResult(HttpServletResponse resp, ModelAndView mv) throws IOException {
        for(ViewResolver viewResolver:viewResolvers){
            if(viewResolver.getFileName().equals(mv.getView())){
                String out=viewResolver.resolve(mv);
                resp.setCharacterEncoding("utf-8");
                resp.getWriter().println(out);
                break;
            }
        }
    }

    private HandlerAdapter getAdapter(HandlerMapping handlerMapping) {
        return  handlerAdapters.get(handlerMapping);
    }

    private HandlerMapping getHandler(HttpServletRequest req) {
       String url= req.getRequestURI();
        for(HandlerMapping handlerMapping:handlerMappings){
            Matcher matcher=handlerMapping.getPattern().matcher(url);
            if(matcher.matches()){
                return handlerMapping;
            }
        }
        return null;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        //完成spring容器
        ApplicationContext context=new ApplicationContext(config.getInitParameter("contextConfiguration"));
        //完成springMVC
        initStrategies(context);
    }

    private void initStrategies(ApplicationContext context) {
        initHandlerMappings(context);
        initHandlerAdapters(context);
        initViewResolvers(context);
    }

    private void initViewResolvers(ApplicationContext context) {
        String template=context.getServletConfig().getProperty("Template");
        String layout= new File(this.getClass().getClassLoader().getResource("").getPath().replace("%20"," ")).getParentFile().getPath()+"/"+template;
        File file=new File(layout);
        for (File f:file.listFiles() ) {
            viewResolvers.add(new ViewResolver(f.getName(),f));
        }
    }
    private void initHandlerAdapters(ApplicationContext context) {
        for (HandlerMapping handlerMapping:handlerMappings ) {
            Annotation[][] pa = handlerMapping.getMethod().getParameterAnnotations();
            Map<String, Integer> paramIndex = new HashMap<>();
            for (int i = 0; i < pa.length; i++) {
                for (Annotation a : pa[i]) {
                    if (a instanceof RequestParam) {
                        paramIndex.put(((RequestParam) a).value(), i);
                    }
                }
            }
            Class<?>[] types = handlerMapping.getMethod().getParameterTypes();
            for (int i = 0; i < types.length; i++) {
                if (types[i] == HttpServletRequest.class || types[i] == HttpServletResponse.class) {
                    paramIndex.put(types[i].getName(), i);
                }
            }
            handlerAdapters.put(handlerMapping,new HandlerAdapter(paramIndex));
        }
    }

    private void initHandlerMappings(ApplicationContext context) {
        Map<String,Object> beanMap=context.getBeanMap();
        for (Map.Entry<String,Object> entry:beanMap.entrySet() ) {
            String baseUrl="";
            Object controller=entry.getValue();
            Class<?> clazz=controller.getClass();
            if(clazz.isAnnotationPresent(Controller.class)){
                RequestMapping requestMapping=clazz.getAnnotation(RequestMapping.class);
                baseUrl=requestMapping.value();
                Method[] methods=clazz.getDeclaredMethods();
                for (Method method:methods) {
                    String methodUrl="";
                    if(method.isAnnotationPresent(RequestMapping.class)){
                        RequestMapping requestMap=method.getAnnotation(RequestMapping.class);
                        methodUrl=requestMap.value();
                        String url=baseUrl+methodUrl;
                        Pattern pattern=Pattern.compile(url);
                        handlerMappings.add(new HandlerMapping(pattern,controller,method));
                    }
                }
            }
        }
    }




}