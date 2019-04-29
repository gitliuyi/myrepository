package framework.beans.support;

import framework.beans.config.BeanDefinition;
import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/*
create by Jack on 2019/4/19
 */
@Data
public class BeanDefinitionReader {

    Properties config=new Properties();
    List<String> classnames=new ArrayList<String>();

    public BeanDefinitionReader(String[] locations) {
        InputStream is=this.getClass().getClassLoader().getResourceAsStream(locations[0]);
        try {
            config.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        doScanner(config.getProperty("scanPackage"));
    }

    private void doScanner(String scanPackage) {
        URL url=this.getClass().getClassLoader().getResource(scanPackage.replaceAll("\\.","/"));
        File classfile=new File(url.getFile().replace("%20"," "));
        for(File f:classfile.listFiles()){
            if(f.isDirectory()){
                doScanner(scanPackage+"."+f.getName());
            }else{
                classnames.add(scanPackage+"."+f.getName().replace(".class",""));
            }
        }
    }
// classnames{demo.DemoService , demo.IDemoService , demo.MyAction}
    public List<BeanDefinition> loadBeanDefinition() {
        List<BeanDefinition> list=new ArrayList<BeanDefinition>();
        for(String classname:classnames){
            try {
                Class<?> clazz=Class.forName(classname);
                if(clazz.isInterface()) continue;
                String beanname=toLowerFirstCase(clazz.getSimpleName());
                BeanDefinition beanDefinition= doCreateBeanDefinition(beanname,classname);
                list.add(beanDefinition);
                for(Class<?> inter:clazz.getInterfaces()){
                    BeanDefinition beandefinition= doCreateBeanDefinition(inter.getName(),classname);
                    list.add(beandefinition);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    private String toLowerFirstCase(String simpleName) {
        char[] chs=simpleName.toCharArray();
        chs[0]+=32;
        return String.valueOf(chs);
    }

    private BeanDefinition doCreateBeanDefinition(String beanname, String classname) {
        return new BeanDefinition(beanname,classname);
    }
}