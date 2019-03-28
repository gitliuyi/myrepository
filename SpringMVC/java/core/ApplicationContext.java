package core;

import Annotation.Autowired;
import Annotation.Controller;
import Annotation.Service;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;

/*
create by Jack on 2019/3/27
 */
public class ApplicationContext {
    private Properties servletConfig=new Properties();
    private List<String> classnames=new ArrayList();
    private Map<String,Object> beanMap=new HashMap();
    private String location;
    public ApplicationContext(String location){
        this.location=location;
        refresh();
    }

    private void refresh() {
        //1.加载配置文件
        loadConfig(location);
        //2.扫描类
        doScanner(servletConfig.getProperty("scanPackage"));
        //3.注册类到IOC中
        registerBean();
        //4.依赖注入
        autowired();
    }

    private void autowired() {
        for (Map.Entry<String,Object> entry:beanMap.entrySet()) {
            Field[] fields=entry.getValue().getClass().getDeclaredFields();
            for(Field field:fields){
                if(field.isAnnotationPresent(Autowired.class)){
                    String fieldname=field.getType().getName();
                    if(beanMap.containsKey(fieldname)){
                        try {
                            field.setAccessible(true);
                            field.set(entry.getValue(),beanMap.get(fieldname));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private void registerBean() {
        for(String name:classnames){
            try {
                Class<?> clazz=Class.forName(name);
                if(clazz.isAnnotationPresent(Controller.class)){
                    beanMap.put(lowFirstCase(clazz.getSimpleName()),clazz.newInstance());
                }else if(clazz.isAnnotationPresent(Service.class)){
                    Object instance=clazz.newInstance();
                    beanMap.put(lowFirstCase(clazz.getSimpleName()),instance);
                    Class<?>[] interfaces=clazz.getInterfaces();
                    for (Class<?> i:interfaces) {
                        beanMap.put(i.getName(),instance);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void doScanner(String scanPackage) {
        URL url= this.getClass().getClassLoader().getResource(scanPackage);
        File classfile=new File(url.getFile().replace("%20"," "));
        for (File f:classfile.listFiles()) {
            classnames.add(scanPackage+"."+f.getName().replaceAll(".class",""));
        }
    }


    private void loadConfig(String contextConfiguration) {
        try {
            InputStream is= this.getClass().getClassLoader().getResourceAsStream(contextConfiguration);
            servletConfig.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String lowFirstCase(String simpleName) {
        char[] chs=simpleName.toCharArray();
        chs[0]+=32;
        return String.valueOf(chs);
    }

    public List<String> getClassnames() {
        return classnames;
    }

    public Map<String, Object> getBeanMap() {
        return beanMap;
    }

    public Properties getServletConfig() {
        return servletConfig;
    }

    public String getLocation() {
        return location;
    }
}