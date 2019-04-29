package framework.beans.config;

import lombok.Data;

/*
create by Jack on 2019/4/19
 */
@Data
public class BeanDefinition {

    private String beanName;
    private String beanClass;
    private boolean isLazyInit=false;

    public BeanDefinition(String beanName, String beanClass) {
        this.beanName = beanName;
        this.beanClass = beanClass;
    }
}