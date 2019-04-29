package framework.webmvc;

import framework.annotation.Controller;
import framework.annotation.RequestMapping;
import framework.annotation.RequestParam;
import framework.context.ApplicationContext;
import framework.webmvc.servlet.HandlerAdapter;
import framework.webmvc.servlet.HandlerMapping;
import framework.webmvc.servlet.ModelAndView;
import framework.webmvc.servlet.ViewResolver;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
create by Jack on 2019/4/20
 */
public class DispatchServlet extends HttpServlet {
    List<HandlerMapping> handlerMappings=new ArrayList<HandlerMapping>();
    Map<HandlerMapping, HandlerAdapter> handlerAdapters=new HashMap<HandlerMapping, HandlerAdapter>();
    List<ViewResolver> viewResolvers=new ArrayList<ViewResolver>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HandlerMapping handler=getHandler(req);
        ModelAndView mv=null;
        if(handler==null){
            Map<String,Object> model=new HashMap();
            model.put("error","没发现您要找的页面哦");
            mv=new ModelAndView("404.html",model);
        }else{
            HandlerAdapter ha=getAdapter(handler);
            mv=ha.hand(req,resp,handler);
        }
        outputResult(resp,mv);
    }

    private void outputResult(HttpServletResponse resp, ModelAndView mv) {
        for (ViewResolver resolver : viewResolvers) {
            if(mv.getView().equals(resolver.getFileName())){
                String out=resolver.solve(mv);
                resp.setCharacterEncoding("UTF-8");
                try {
                    resp.getWriter().println(out);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }

    }

    private HandlerAdapter getAdapter(HandlerMapping handler) {
        return handlerAdapters.get(handler);
    }

    private HandlerMapping getHandler(HttpServletRequest req) {
        String url=req.getRequestURI();
        for (HandlerMapping handlerMapping : handlerMappings) {
            Matcher matcher=handlerMapping.getPattern().matcher(url);
            if(matcher.matches()){
                return handlerMapping;
            }
        }
        return null;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ApplicationContext context = new ApplicationContext(config.getInitParameter("contextConfiguration"));
        initStrategies(context);
    }

    private void initStrategies(ApplicationContext context) {
        initHandlerMappings(context);
        initHandlerAdapters(context);
        initViewResolvers(context);
    }

    private void initViewResolvers(ApplicationContext context) {
        String template=context.getReader().getConfig().getProperty("Template");
        String path=new File(this.getClass().getClassLoader().getResource("").getPath().replace("%20"," ")).getParentFile().getPath()+"/"+template;
        File layout=new File(path);
        for (File file : layout.listFiles()) {
            viewResolvers.add(new ViewResolver(file.getName(),file));
        }
    }

    private void initHandlerAdapters(ApplicationContext context) {
        for (HandlerMapping handlerMapping : handlerMappings) {
            Map<String,Integer> paramIndex=new HashMap<String,Integer>();
            Annotation[][] pa = handlerMapping.getMethod().getParameterAnnotations();
            for(int i=0;i<pa.length;i++){
                for (Annotation an : pa[i]) {
                    if(an instanceof RequestParam){
                       paramIndex.put(((RequestParam) an).value(),i);
                    }
                }
            }
            Class<?>[] classes= handlerMapping.getMethod().getParameterTypes();
            for(int i=0;i<classes.length;i++){
                if(classes[i]==HttpServletRequest.class||classes[i]==HttpServletResponse.class){
                    paramIndex.put(classes[i].getSimpleName(),i);
                }
            }
            handlerAdapters.put(handlerMapping,new HandlerAdapter(paramIndex));
        }
    }

    private void initHandlerMappings(ApplicationContext context) {
        String[] beanNames = context.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            Object controller = context.getBean(beanName);
            Class<?> clazz = controller.getClass();
            if (clazz.isAnnotationPresent(Controller.class) && clazz.isAnnotationPresent(RequestMapping.class)) {
                String baseUrl = clazz.getAnnotation(RequestMapping.class).value();
                Method[] methods = clazz.getDeclaredMethods();
                for (Method method : methods) {
                    if(method.isAnnotationPresent(RequestMapping.class)){
                        String methodUrl=method.getAnnotation(RequestMapping.class).value();
                        String url=baseUrl+methodUrl;
                        Pattern pattern=Pattern.compile(url);
                        handlerMappings.add(new HandlerMapping(pattern,controller,method));
                    }

                }
            }
        }

    }
}