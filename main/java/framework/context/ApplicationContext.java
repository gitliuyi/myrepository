package framework.context;

import framework.annotation.Autowired;
import framework.aop.AopProxy;
import framework.aop.CglibAopProxy;
import framework.aop.JdkDynamicAopProxy;
import framework.aop.config.AopConfig;
import framework.aop.support.AdvisedSupport;
import framework.beans.BeanFactory;
import framework.beans.config.BeanDefinition;
import framework.beans.support.BeanDefinitionReader;
import framework.beans.support.DefaultListableBeanFactory;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
create by Jack on 2019/4/19
 */

public class ApplicationContext extends DefaultListableBeanFactory implements BeanFactory {
    String[] locations;
    BeanDefinitionReader reader;
    Map<String, Object> ioc = new HashMap<String, Object>();

    public BeanDefinitionReader getReader() {
        return reader;
    }

    public ApplicationContext(String... locations) {
        this.locations = locations;
        refresh();
    }

    @Override
    public void refresh() {
        //定位
        reader = new BeanDefinitionReader(locations);
        //加载
        List<BeanDefinition> beanDefinitions = reader.loadBeanDefinition();
        //注册
        doRegisterBeanDefinition(beanDefinitions);
        //实例化、依赖注入
        doAutowired();
    }

    private void doAutowired() {
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            if (!entry.getValue().isLazyInit()) {
                getBean(entry.getKey());
            }
        }
    }

    private void doRegisterBeanDefinition(List<BeanDefinition> beanDefinitions) {
        for (BeanDefinition beanDefinition : beanDefinitions) {
            beanDefinitionMap.put(beanDefinition.getBeanName(), beanDefinition);
        }
    }

    @Override
    public Object getBean(String beanName) {
        Object instance = ioc.get(beanName);
        if (instance == null) {
            instance = instantiateBean(beanName);
            populateBean(instance);
            return instance;
        }
        return instance;
    }

    private void populateBean(Object instance) {
        Field[] fields = instance.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Autowired.class)) {
                try {
                    field.setAccessible(true);
                    field.set(instance, getBean(field.getName()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Object instantiateBean(String beanName) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        String classname = beanDefinition.getBeanClass();
        Object instance = null;
        try {
            Class<?> clazz = Class.forName(classname);
            instance = clazz.newInstance();
            AdvisedSupport config = instantionAopConfig();
            config.setTargetClass(clazz);
            config.setTarget(instance);
            if (config.pointCutMatch()) {
               instance = createProxy(config).getProxy();
            }
            ioc.put(beanName, instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    private AopProxy createProxy(AdvisedSupport config) {
        Class<?> clazz=config.getTargetClass();
        if(clazz.getInterfaces().length>0){
            return new JdkDynamicAopProxy(config);
        }else{
            return new CglibAopProxy(config);
        }
    }

    private AdvisedSupport instantionAopConfig() {
        AopConfig config=new AopConfig();
        config.setPointCut(this.reader.getConfig().getProperty("pointCut"));
        config.setAspectClass(this.reader.getConfig().getProperty("aspectClass"));
        config.setAspectBefore(this.reader.getConfig().getProperty("aspectBefore"));
        config.setAspectAfter(this.reader.getConfig().getProperty("aspectAfter"));
        return new AdvisedSupport(config);
    }

    private String toLowerFirstCase(String simpleName) {
        char[] chs = simpleName.toCharArray();
        chs[0] += 32;
        return String.valueOf(chs);
    }

    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[beanDefinitionMap.size()]);
    }
}