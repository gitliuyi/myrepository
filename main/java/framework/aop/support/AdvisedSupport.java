package framework.aop.support;

import framework.aop.aspect.MethodAfterAdviceInteceptor;
import framework.aop.aspect.MethodBeforeAdviceInteceptor;
import framework.aop.config.AopConfig;
import lombok.Data;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/*
create by Jack on 2019/4/21
 */
@Data
public class AdvisedSupport {
    private Class<?> targetClass;
    private Object target;
    private AopConfig config;
    private Pattern pointCutClassPattern;
    private Map<Method,List<Object>> methodCache;

    public AdvisedSupport(AopConfig config) {
        this.config=config;
    }

    public void setTargetClass(Class<?> clazz) {
        this.targetClass=clazz;
        parse();
    }

    private void parse() {
        methodCache=new HashMap<>();
        String pointCut=config.getPointCut();
        String pointCutClass= pointCut.substring(0,pointCut.lastIndexOf("(")-3);
        pointCutClassPattern=Pattern.compile("class "+pointCutClass.substring(pointCutClass.lastIndexOf(" ")+1));
        Method[] methods=this.targetClass.getDeclaredMethods();
        Pattern pattern=Pattern.compile(pointCut);
        Map<String,Method> methodMap=new HashMap<>();
        try {
            Class<?> clazz=Class.forName(this.config.getAspectClass());
            Object aspectTarget=clazz.newInstance();
            Method[] ms=clazz.getDeclaredMethods();
            for (Method m : ms) {
                methodMap.put(m.getName(),m);
            }
            for (Method method : methods) {
                if(pattern.matcher(method.toString()).matches()){
                    List<Object> list=new ArrayList<>();
                    list.add(new MethodBeforeAdviceInteceptor(methodMap.get(config.getAspectBefore()),aspectTarget));
                    list.add(new MethodAfterAdviceInteceptor(methodMap.get(config.getAspectAfter()),aspectTarget));
                    this.methodCache.put(method,list);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTarget(Object target) {
        this.target=target;
    }

    public boolean pointCutMatch() {
        return pointCutClassPattern.matcher(targetClass.toString()).matches();
    }


    public List<Object> getAdvices(Method method, Class<?> targetClass) {
        List<Object> cached=methodCache.get(method);
        if(cached==null){
            try {
                Method m=targetClass.getMethod(method.getName(),method.getParameterTypes());
                cached=methodCache.get(m);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cached;
    }
}