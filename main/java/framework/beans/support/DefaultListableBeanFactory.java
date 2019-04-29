package framework.beans.support;

import framework.beans.config.BeanDefinition;
import framework.context.support.AbstractApplicationContext;

import java.util.HashMap;
import java.util.Map;

/*
create by Jack on 2019/4/19
 */
public class DefaultListableBeanFactory extends AbstractApplicationContext {

    public final Map<String, BeanDefinition> beanDefinitionMap=new HashMap<String, BeanDefinition>();
}