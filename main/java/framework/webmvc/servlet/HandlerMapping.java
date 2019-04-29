package framework.webmvc.servlet;

import lombok.Data;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/*
create by Jack on 2019/4/20
 */
@Data
public class HandlerMapping {
    private Pattern pattern;
    private Object Controller;
    private Method method;

    public HandlerMapping(Pattern pattern, Object controller, Method method) {
        this.pattern = pattern;
        Controller = controller;
        this.method = method;
    }
}